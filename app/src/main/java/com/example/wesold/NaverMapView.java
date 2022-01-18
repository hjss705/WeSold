package com.example.wesold;

import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapView;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.util.FusedLocationSource;

public class NaverMapView extends AppCompatActivity implements OnMapReadyCallback {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private FusedLocationSource locationSource;
    private com.naver.maps.map.NaverMap naverMap;
    private MapView mapView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navermap);

        mapView = findViewById(R.id.map_View);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);
        locationSource =
                new FusedLocationSource(this,LOCATION_PERMISSION_REQUEST_CODE);

    }


    public void onRequestPerMissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[]
                                                   grantResults) {
        if(locationSource.onRequestPermissionsResult(
                requestCode, permissions, grantResults)) {
        }
        super.onRequestPermissionsResult(
                requestCode,permissions,grantResults);
    }


    @Override
    public void onMapReady(@NonNull com.naver.maps.map.NaverMap naverMap) {
        this.naverMap = naverMap;
        naverMap.setLocationSource(locationSource);
        naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);

    }
}

