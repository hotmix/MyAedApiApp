package jp.hotmix.myaedapiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapViewActivity extends MapsActivity {
    private GoogleMap mMap;
    private LatLng aedLocation;
    private String locationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);

        Intent intent = getIntent();
        locationName = intent.getStringExtra("locationName");
        aedLocation = new LatLng(
                Double.parseDouble(intent.getStringExtra("Lat")),
                Double.parseDouble(intent.getStringExtra("Lon"))
        );

        MapView map = (MapView)findViewById(R.id.mapView);
        map.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        super.onMapReady(googleMap);

        mMap = googleMap;

        UiSettings ui = mMap.getUiSettings();

        ui.setZoomControlsEnabled(true);

        // Add a marker in Sydney and move the camera

        mMap.addMarker(new MarkerOptions().position(aedLocation).title(locationName));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(aedLocation, 15));



    }
}
