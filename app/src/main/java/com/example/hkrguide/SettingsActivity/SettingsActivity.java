package com.example.hkrguide.SettingsActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.example.hkrguide.BaseActivity;
import com.example.hkrguide.R;
import com.google.android.material.navigation.NavigationView;

public class SettingsActivity extends BaseActivity {

    @Override
    public void onResume() {
        // Initialize Super Class
        super.onResume();
        // Set Checked Item
        ((NavigationView) findViewById(R.id.nav_view)).setCheckedItem(R.id.nav_item_settings);
        super.currentNavItem = R.id.nav_item_settings;
        // Set Toolbar Text
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.toobar_title_settings);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialize Super Class
        super.onCreate(savedInstanceState);
        // Inflate Layout into Parent Frame Layout
        FrameLayout frameLayout = findViewById(R.id.activity_frame);
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View activityView = layoutInflater.inflate(R.layout.activity_settings, findViewById(R.id.root_settings), false);
        frameLayout.addView(activityView);

        /* Activity Code */

    }
}