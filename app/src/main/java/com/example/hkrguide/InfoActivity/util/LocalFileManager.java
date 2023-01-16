package com.example.hkrguide.InfoActivity.util;

import android.content.Context;

import com.example.hkrguide.R;

import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

// Methods to manipulate local files

public class LocalFileManager {

    // Download a file from a URL
    // Replaces the target file if it exists
    public static void downloadFile(URL url, Path target) throws IOException {
        URLConnection connection = url.openConnection();
        connection.connect();
        Files.copy(new BufferedInputStream(connection.getInputStream()), target, StandardCopyOption.REPLACE_EXISTING);
    }

    // Copy the asset XML to local storage
    // Replaces the cached local XML if present
    public static void copyAssetXmlToStorage(Context context) throws IOException {
        Files.copy(new BufferedInputStream(context.getAssets().open(context.getString(R.string.file_hkr_info_default))), context.getFilesDir().toPath().resolve(context.getString(R.string.file_hkr_info_local_cache)), StandardCopyOption.REPLACE_EXISTING);
    }

    // Copy the cached online XML to local storage
    // Replaces the cached local XML if present
    public static void copyOnlineXmlToCache(Context context) throws IOException {
        Files.copy(context.getFilesDir().toPath().resolve(context.getString(R.string.file_hkr_info_online_cache)), context.getFilesDir().toPath().resolve(context.getString(R.string.file_hkr_info_local_cache)), StandardCopyOption.REPLACE_EXISTING);
    }

    // Read the local XML
    public static ContentXmlManager getLocalXml(Context context) {
        // Get Local XML Path
        Path localXmlPath = context.getFilesDir().toPath().resolve(context.getString(R.string.file_hkr_info_local_cache));
        ContentXmlManager localXml = null;
        if (Files.isRegularFile(localXmlPath)) {
            // Try to load the XML if it exists
            try {
                localXml = new ContentXmlManager(new BufferedInputStream(Files.newInputStream(localXmlPath, StandardOpenOption.READ)));
            } catch (IOException | XmlPullParserException ignored) {
            }
        }

        return localXml;
    }

    // Read the cached online XML
    public static ContentXmlManager getCachedOnlineXml(Context context) {
        // Get Online XML Path
        Path onlineXmlPath = context.getFilesDir().toPath().resolve(context.getString(R.string.file_hkr_info_online_cache));
        ContentXmlManager onlineXml = null;
        if (Files.isRegularFile(onlineXmlPath)) {
            // Try to load the XML if it exists
            try {
                onlineXml = new ContentXmlManager(new BufferedInputStream(Files.newInputStream(onlineXmlPath, StandardOpenOption.READ)));
            } catch (IOException | XmlPullParserException ignored) {
            }
        }

        return onlineXml;
    }

    // Read the asset XML
    public static ContentXmlManager getAssetXml(Context context) {
        // Try to load the asset XML
        ContentXmlManager assetXml = null;
        try {
            assetXml = new ContentXmlManager(new BufferedInputStream(context.getAssets().open(context.getString(R.string.file_hkr_info_default))));
        } catch (IOException | XmlPullParserException ignored) {
        }

        return assetXml;
    }
}
