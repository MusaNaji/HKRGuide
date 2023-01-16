package com.example.hkrguide.InfoActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.hkrguide.BaseActivity;
import com.example.hkrguide.InfoActivity.fragments.LoadingInfoFragment;
import com.example.hkrguide.InfoActivity.util.LocalFileManager;
import com.example.hkrguide.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class InfoActivity extends BaseActivity {

    @Override
    public void onResume() {
        // Initialize Super Class
        super.onResume();
        // Set Checked Item in Nav Drawer
        ((NavigationView) findViewById(R.id.nav_view)).setCheckedItem(R.id.nav_item_info);
        super.currentNavItem = R.id.nav_item_info;
        // Set Toolbar Text
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.toobar_title_info);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialize Super Class
        super.onCreate(savedInstanceState);
        // Inflate Layout into Parent Frame Layout
        FrameLayout frameLayout = findViewById(R.id.activity_frame);
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View activityView = layoutInflater.inflate(R.layout.activity_info, findViewById(R.id.root_info), false);
        frameLayout.addView(activityView);

        /* Activity Code */

        // Initialize Update Button
        FloatingActionButton updateButton = findViewById(R.id.button_update);

        // Routine to update the data XML when the button is pressed
        // Semaphore to prevent multiple execution
        Semaphore updateSemaphore = new Semaphore(1);

        updateButton.setOnClickListener(view -> {
            // Only Execute if the Semaphore can be Acquired
            if (updateSemaphore.tryAcquire()) {
                // Make Toast to inform user
                Toast.makeText(view.getContext(), "Updating Info Page", Toast.LENGTH_SHORT).show();

                Log.i("Update XML", "Updating XML From Server");

                // Prepare Executors
                ExecutorService primaryExecutor = Executors.newSingleThreadExecutor();
                ExecutorService auxiliaryExecutor = Executors.newSingleThreadExecutor();

                // Submit task that downloads the XML file to Primary Executor
                primaryExecutor.submit(() -> {
                    try {
                        // Download XML File
                        LocalFileManager.downloadFile(new URL(getString(R.string.url_hkr_info_online)), getFilesDir().toPath().resolve(getString(R.string.file_hkr_info_online_cache)));
                        // Goto Loading Fragment
                        getSupportFragmentManager().beginTransaction().replace(R.id.infoFragment, new LoadingInfoFragment()).commit();
                    } catch (IOException err) {
                        // Failed to Load
                        Log.e("Update XML", err.toString());
                        // Inform User
                        Toast.makeText(view.getContext(), "Update Failed", Toast.LENGTH_SHORT).show();
                    } finally {
                        // Release Update Semaphore
                        updateSemaphore.release();
                    }
                });

                // Submit a watchdog task to Auxiliary Executor
                auxiliaryExecutor.submit(() -> {
                    try {
                        // Wait 30 seconds for Update Task to terminate
                        primaryExecutor.shutdown();
                        primaryExecutor.awaitTermination(30, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        Log.e("Update XML Watchdog", "Interrupted when waiting Update XML to finish");
                    } finally {
                        // If the task is not terminated, force termination
                        if (!primaryExecutor.isTerminated()) {
                            Log.e("Update XML Watchdog", "Update XML took too long, terminating");
                        }
                        primaryExecutor.shutdownNow();
                    }
                });

                auxiliaryExecutor.shutdown();
            }
        });
    }
}