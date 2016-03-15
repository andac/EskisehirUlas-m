package com.example.dell_.esesulasim;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        mMap = googleMap;

        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);



        LatLng Eskisehir = new LatLng(39.7545196, 30.5081433);
        LatLng Muhendislik = new LatLng(39.7513013, 30.4771206);
        LatLng tip = new LatLng(39.7525002, 30.488825);
        LatLng egitim = new LatLng(39.7526095, 30.4845871);
        LatLng Ibf = new LatLng(39.7486917, 30.4835196);


        mMap.addMarker(new MarkerOptions().position(Eskisehir).title(" Eskişehir"));
        mMap.addMarker(new MarkerOptions().position(Muhendislik).title(" Mühendislik"));
        mMap.addMarker(new MarkerOptions().position(tip).title(" Tıp Fakültesi"));
        mMap.addMarker(new MarkerOptions().position(egitim).title(" Eğitim"));
        mMap.addMarker(new MarkerOptions().position(Ibf).title("İBF"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(Eskisehir));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(Muhendislik));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tip));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(egitim));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Ibf));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Eskisehir,13));
    }
}
