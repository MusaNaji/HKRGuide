package com.example.hkrguide.InfoActivity.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.hkrguide.InfoActivity.util.ContentXmlManager;
import com.example.hkrguide.InfoActivity.util.LocalFileManager;
import com.example.hkrguide.InfoActivity.util.MenuEntry;
import com.example.hkrguide.R;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// Info Screen Loading Fragment

public class LoadingInfoFragment extends Fragment {

    // Loaded Menu Entries
    // Will be passed to First Fragment
    List<MenuEntry> entries = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loading_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView loadingText = view.findViewById(R.id.text_loading);
        ProgressBar loadingProgressBar = view.findViewById(R.id.progress_loading);

        // Prepare Executors
        ExecutorService primaryExecutor = Executors.newSingleThreadExecutor();
        ExecutorService auxiliaryExecutor = Executors.newSingleThreadExecutor();

        // Submit task to resolve and load the latest XML file
        // And switch to First Fragment to Primary Executor
        primaryExecutor.submit(() -> {
            try {
                Thread.sleep(100);

                // Resolve, Load and Parse the correct XML file
                loadingText.post(() -> loadingText.setText("Resolving XML"));
                Thread.sleep(100);

                entries = resolveXmlFile();

                // Bundle Menu Entries
                loadingText.post(() -> loadingText.setText("Bundling Information"));
                Thread.sleep(100);

                Bundle bundle = new Bundle();
                bundle.putSerializable("entries", (Serializable) entries);

                // Pass bundle to new First Fragment instance
                FirstFragment fragment = new FirstFragment();
                fragment.setArguments(bundle);

                // Switch to the new First Fragment instance
                loadingText.post(() -> loadingText.setText("Switching Fragment"));
                Thread.sleep(100);

                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.infoFragment, fragment).commit();

            } catch (InterruptedException | IllegalStateException | IOException e) {
                // Failed to Load
                Log.e("XML Loader", e.toString());
                loadingText.post(() -> loadingText.setText("Loading Failed"));
            }
        });

        // Submit a watchdog task to Auxiliary Executor
        auxiliaryExecutor.submit(() -> {
            try {
                // Wait 30 seconds for Update Task to terminate
                primaryExecutor.shutdown();
                primaryExecutor.awaitTermination(30, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Log.e("XML Loader Watchdog", "Interrupted when waiting XML Loader to finish");
            } finally {
                // If the task is not terminated, force termination
                if (!primaryExecutor.isTerminated()) {
                    Log.e("XML Loader Watchdog", "XML Loader took too long, terminating");
                }
                primaryExecutor.shutdownNow();
            }
        });

        auxiliaryExecutor.shutdown();
    }

    // Find the latest XML and replace local cache with it
    private List<MenuEntry> resolveXmlFile() throws IOException {

        // Get Online XML
        ContentXmlManager onlineXml = LocalFileManager.getCachedOnlineXml(requireContext());

        // Get Asset XML
        ContentXmlManager assetXml = LocalFileManager.getAssetXml(requireContext());

        // Get Local XML
        ContentXmlManager localXml = LocalFileManager.getLocalXml(requireContext());

        ContentXmlManager newestXml;

        // Determine if the online XML or the asset XML is newer
        int newest1 = newest(onlineXml, assetXml);
        if (newest1 == 2) {
            // If the asset XML is newer
            // Determine if the local XML or the asset XML is newer
            int newest2 = newest(localXml, assetXml);
            if (newest2 == 2) {
                // If the asset XML is newer
                // Overwrite local XML
                LocalFileManager.copyAssetXmlToStorage(requireContext());
                // Asset XML is the newest
                newestXml = assetXml;
            } else {
                // Local XML is the newest
                newestXml = localXml;
            }
        } else {
            // If the online XML is newer
            // Determine if the local XML or the online XML is newer
            int newest2 = newest(localXml, onlineXml);
            if (newest2 == 2) {
                // If the online XML is newer
                // Overwrite local XML
                LocalFileManager.copyOnlineXmlToCache(requireContext());
                // Online XML is the newest
                newestXml = onlineXml;
            } else {
                // Local XML is the newest
                newestXml = localXml;
            }
        }

        if (newestXml == null) {
            throw new IllegalStateException();
        }

        // Return the Menu Entries in the newest XML
        return newestXml.getMenuEntries();
    }

    // Return which of the two input xml is newer
    // Return 1 for xml1, 2 for xml2, 0 on equal or both null
    // If one of the xml is null, returns the index of the other xml
    private int newest(ContentXmlManager xml1, ContentXmlManager xml2) {
        if (xml1 == null) {
            if (xml2 != null) {
                return 2;
            } else {
                return 0;
            }
        } else if (xml2 == null) {
            return 1;
        } else {
            int compareResult = xml1.getLastModifiedTime().compareTo(xml2.getLastModifiedTime());
            if (compareResult > 0) {
                return 1;
            } else if (compareResult < 0) {
                return 2;
            } else {
                return 0;
            }
        }
    }
}
