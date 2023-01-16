package com.example.hkrguide.MapActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.hkrguide.BaseActivity;
import com.example.hkrguide.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.List;

public class MapActivity extends BaseActivity implements OnMapReadyCallback {
    GoogleMap map;
    SupportMapFragment mapFragment;
    SearchView searchView;
    ImageView changeMapMode;

    @Override
    public void onResume() {
        // Initialize Super Class
        super.onResume();
        // Set Checked Item
        ((NavigationView) findViewById(R.id.nav_view)).setCheckedItem(R.id.nav_item_map);
        super.currentNavItem = R.id.nav_item_map;
        // Set Toolbar Text
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.toobar_title_map);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialize Super Class
        super.onCreate(savedInstanceState);
        // Inflate Layout into Parent Frame Layout
        FrameLayout frameLayout = findViewById(R.id.activity_frame);
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View activityView = layoutInflater.inflate(R.layout.activity_map, findViewById(R.id.root_map), false);
        frameLayout.addView(activityView);

        /* Activity Code */

        searchView = findViewById(R.id.searchView);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = searchView.getQuery().toString();
                List<Address> addressList = null;

                if (!location.equals("")) {
                    Geocoder geocoder = new Geocoder(MapActivity.this);

                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (addressList.size() > 0) {
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    map.addMarker(new MarkerOptions().position(latLng).title(location));
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f));

                } else {
                    Toast.makeText(MapActivity.this, "Not Found!", Toast.LENGTH_LONG).show();
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        changeMapMode = (ImageView) findViewById(R.id.imageView);
        changeMapMode.setOnClickListener(v -> {
            if (map.getMapType() == GoogleMap.MAP_TYPE_NORMAL) {
                map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                ImageViewCompat.setImageTintList(changeMapMode, ColorStateList.valueOf(ContextCompat.getColor(this, R.color.white)));
            } else if (map.getMapType() == GoogleMap.MAP_TYPE_HYBRID) {
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                ImageViewCompat.setImageTintList(changeMapMode, ColorStateList.valueOf(ContextCompat.getColor(this, R.color.grey_mid)));
            }


        });
        //Sets a callback object which will be triggered when the GoogleMap instance is ready to be used.
        mapFragment.getMapAsync(MapActivity.this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        //HKR buildings.
        final LatLng hus_4 = new LatLng(56.04756139213809, 14.146349236549904);
        final LatLng hus_5 = new LatLng(56.047967473335426, 14.147732129629363);
        final LatLng hus_6 = new LatLng(56.04789463457094, 14.144891417437954);
        final LatLng hus_7 = new LatLng(56.04851230025038, 14.14628679943329);
        final LatLng hus_9 = new LatLng(56.04920343191042, 14.146722871701161);
        final LatLng hus_11 = new LatLng(56.04889723018199, 14.146842939795915);
        final LatLng hus_12 = new LatLng(56.04890359718987, 14.14515061467395);
        final LatLng hus_13 = new LatLng(56.04895312023563, 14.147364154329587);
        final LatLng hus_14 = new LatLng(56.04733988922652, 14.144933814927567);
        final LatLng hus_15 = new LatLng(56.0474006358109, 14.145767928208418);
        final LatLng hus_16 = new LatLng(56.04712573620995, 14.145346521019537);
        final LatLng hus_17 = new LatLng(56.0474646724281, 14.146969700690002);
        final LatLng hus_18 = new LatLng(56.047422514511666, 14.147817094270492);
        final LatLng hus_19 = new LatLng(56.047174113790824, 14.147417150720086);
        final LatLng hus_20 = new LatLng(56.04845454160334, 14.144878279463432);
        final LatLng hus_21 = new LatLng(56.048528638482495, 14.147723846464393);
        final LatLng hus_117 = new LatLng(56.049341343747464, 14.147080457994326);
        // move map course to building 4.
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(hus_7, 16f));

        // marker for each building.
        Marker building_4 = map.addMarker(
                new MarkerOptions()
                        .position(hus_4).title("Building nr: 4").snippet("Administration & Services.")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        Marker building_5 = map.addMarker(
                new MarkerOptions()
                        .position(hus_5).title("Building nr: 5")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        Marker building_6 = map.addMarker(
                new MarkerOptions()
                        .position(hus_6).title("Building nr: 6")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        Marker building_7 = map.addMarker(
                new MarkerOptions()
                        .position(hus_7).title("Building nr: 7").snippet("Reception, Student Council, Café.")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        building_7.showInfoWindow();

        Marker building_9 = map.addMarker(
                new MarkerOptions()
                        .position(hus_9).title("Building nr: 9").snippet("IT-Support.")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        Marker building_11 = map.addMarker(
                new MarkerOptions()
                        .position(hus_11).title("Building nr: 11")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        Marker building_12 = map.addMarker(
                new MarkerOptions()
                        .position(hus_12).title("Building nr: 12").snippet("Laboratories.")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        Marker building_13 = map.addMarker(
                new MarkerOptions()
                        .position(hus_13).title("Building nr: 13").snippet("Health Services.")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        Marker building_14 = map.addMarker(
                new MarkerOptions()
                        .position(hus_14).title("Building nr: 14")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        Marker building_15 = map.addMarker(
                new MarkerOptions()
                        .position(hus_15).title("Building nr: 15").snippet("Housing & Exchange Student Services, Student Union.")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        Marker building_16 = map.addMarker(
                new MarkerOptions()
                        .position(hus_16).title("Building nr: 16")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        Marker building_17 = map.addMarker(
                new MarkerOptions()
                        .position(hus_17).title("Building nr: 17")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        Marker building_18 = map.addMarker(
                new MarkerOptions()
                        .position(hus_18).title("Building nr: 18").snippet("Study Administration (Ladok & Ubas)")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        Marker building_19 = map.addMarker(
                new MarkerOptions()
                        .position(hus_19).title("Building nr: 19")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        Marker building_20 = map.addMarker(
                new MarkerOptions()
                        .position(hus_20).title("Building nr: 20").snippet("Laboratory.")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        Marker building_21 = map.addMarker(
                new MarkerOptions()
                        .position(hus_21).title("Building nr: 21")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        Marker building_117 = map.addMarker(
                new MarkerOptions()
                        .position(hus_117).title("Building nr: 117")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        map.setOnInfoWindowClickListener(marker -> {
            if (marker.getTitle().contains("Building")) {
                Uri uri9 = Uri.parse("https://www.hkr.se/globalassets/dokument-hogskolegemensam/hkr_map.pdf");// sending to Deal Website
                Intent i9 = new Intent(Intent.ACTION_VIEW, uri9);
                startActivity(i9);
            }
        });
    }
}