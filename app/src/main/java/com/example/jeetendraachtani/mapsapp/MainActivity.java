package com.example.jeetendraachtani.mapsapp;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {


    GoogleMap mgoogleMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(GoogleServicesAvailable()){
            Toast.makeText(this, "PEFFECT!!!", Toast.LENGTH_SHORT).show();
            setContentView(R.layout.activity_main);
            initMap();
        }else{
            // No GOOGLE MAPs LAYOUT
        }

    }

    private void initMap() {

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapfragment);
        mapFragment.getMapAsync(this);
    }

    public boolean GoogleServicesAvailable(){
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);
        if(isAvailable== ConnectionResult.SUCCESS){
            return true;
        }else if(api.isUserResolvableError(isAvailable)){

            Dialog dialog= api.getErrorDialog(this, isAvailable,0);
            dialog.show();
        }else{
            Toast.makeText(this, "Cant Connect to Play Services", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mgoogleMap = googleMap;
        goToLocationZoom(23.038596, 72.528570,15);
    }

    private void goToLocationZoom(double v, double v1, float zoom) {

        LatLng ll = new LatLng(v,v1);
        CameraUpdate  update = CameraUpdateFactory.newLatLngZoom(ll,zoom);
        mgoogleMap.moveCamera(update);
    }

    private void goToLocation(double v, double v1) {
        LatLng ll = new LatLng(v,v1);
        CameraUpdate  update = CameraUpdateFactory.newLatLng(ll);
        mgoogleMap.moveCamera(update);

    }
}
