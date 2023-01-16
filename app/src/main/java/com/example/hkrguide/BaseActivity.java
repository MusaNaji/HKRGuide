package com.example.hkrguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.hkrguide.InfoActivity.InfoActivity;
import com.example.hkrguide.MapActivity.MapActivity;
import com.example.hkrguide.SettingsActivity.SettingsActivity;
import com.example.hkrguide.ToolsActivity.ToolsActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.Map;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout navDrawer;
    protected int currentNavItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navView = findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);

        navDrawer = findViewById(R.id.nav_drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, navDrawer, toolbar, R.string.nav_toggle_open, R.string.nav_toggle_close);
        navDrawer.addDrawerListener(toggle);

        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (navDrawer.isDrawerOpen(GravityCompat.START)) {
            navDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        // Switch Statement Implementation
        int id = item.getItemId();
        if (id != currentNavItem) {
            if (id == R.id.nav_item_map) {
                intent = new Intent(this, MapActivity.class);
            } else if (id == R.id.nav_item_info) {
                intent = new Intent(this, InfoActivity.class);
            } else if (id == R.id.nav_item_tools) {
                intent = new Intent(this, ToolsActivity.class);
            } else if (id == R.id.nav_item_settings) {
                intent = new Intent(this, SettingsActivity.class);
            } else {
                return false;
            }

            navDrawer.closeDrawer(GravityCompat.START);

            new Handler().postDelayed(() -> {
                startActivity(intent);
            }, 300);
        } else {
            navDrawer.closeDrawer(GravityCompat.START);
        }

        return true;
    }
}