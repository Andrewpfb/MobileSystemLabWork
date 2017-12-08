package example.mapslocation;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.Date;

import example.mapslocation.Models.LocationInfo;
import example.mapslocation.Services.LocationService;
import example.mapslocation.Utils.CommonLocationList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button startBtn, stopBtn, downBtn;
    private static final int REQUEST_ACCESS_FINE_LOCATION = 10001;
    private static final String ACCESS_FINE_LOCATION_PERMISSION = Manifest.permission.ACCESS_FINE_LOCATION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        startBtn = (Button) findViewById(R.id.startBtn);
        stopBtn = (Button) findViewById(R.id.stopBtn);
        downBtn = (Button) findViewById(R.id.downBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPermissionGranted(ACCESS_FINE_LOCATION_PERMISSION)) {
                    Thread myThread = new Thread(
                            new Runnable() {
                                public void run() {
                                    startService(new Intent(getApplicationContext(), LocationService.class));
                                }
                            }
                    );
                    myThread.start();
                } else {
                    Toast.makeText(MapsActivity.this, "This application needs access to the your location", Toast.LENGTH_SHORT).show();
                    requestPermission(ACCESS_FINE_LOCATION_PERMISSION, REQUEST_ACCESS_FINE_LOCATION);
                }
            }
        });
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(getApplicationContext(), LocationService.class));
                DrawMarkers();
            }
        });
        downBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),DownloadActivity.class));
            }
        });
    }

    private boolean isPermissionGranted(String permission) {
        int permissionCheck = ActivityCompat.checkSelfPermission(this, permission);
        return permissionCheck == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_ACCESS_FINE_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MapsActivity.this, "Permissions obtained", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MapsActivity.this, "Permissions don't obtained", Toast.LENGTH_LONG).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void requestPermission(String permission, int requestCode) {
        // запрашиваем разрешение
        ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
    }


    public void DrawMarkers() {
        int size = CommonLocationList.locationInfos.size();
        PolylineOptions line = new PolylineOptions();
        line.width(4f).color(R.color.colorPrimary);
        for (LocationInfo locInfo : CommonLocationList.locationInfos) {
            if (locInfo == CommonLocationList.locationInfos.get(0)) {
                LatLng marker = new LatLng(locInfo.getLatitude(), locInfo.getLongitude());
                mMap.addMarker(new MarkerOptions().
                        position(marker).
                        title("Track").
                        snippet(locInfo.getDate().toString()).
                        icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
            } else if (locInfo == CommonLocationList.locationInfos.get(size - 1)) {
                LatLng marker = new LatLng(locInfo.getLatitude(), locInfo.getLongitude());
                mMap.addMarker(new MarkerOptions().
                        position(marker).
                        title("Track").
                        snippet(locInfo.getDate().toString()).
                        icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
            } else {
                LatLng marker = new LatLng(locInfo.getLatitude(), locInfo.getLongitude());
                mMap.addMarker(new MarkerOptions().
                        position(marker).
                        title("Track").
                        snippet(locInfo.getDate().toString()));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
            }
            line.add(new LatLng(locInfo.getLatitude(), locInfo.getLongitude()));
            Toast.makeText(getApplicationContext(), locInfo.toString(), Toast.LENGTH_SHORT).show();
        }
        mMap.addPolyline(line);
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
