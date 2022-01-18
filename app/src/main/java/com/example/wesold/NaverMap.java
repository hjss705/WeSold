package com.example.wesold;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.naver.maps.map.MapView;

public class NaverMap extends AppCompatActivity {
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navermap);

        mapView = findViewById(R.id.map_View);
        mapView.onCreate(savedInstanceState);
    }
}

