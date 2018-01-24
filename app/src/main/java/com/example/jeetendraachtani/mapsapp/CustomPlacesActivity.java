package com.example.jeetendraachtani.mapsapp;

import android.*;
import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.dynamiclinks.DynamicLink;

public class CustomPlacesActivity extends AppCompatActivity {


   private static final  String TAG="CustomPlacesActivity";
   private static final int ERROR_DIALOG_REQUEST=9001;

   Button btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_places);
        btn=findViewById(R.id.btn_GoTOMaps);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isServicesOK()){
                    Toast.makeText(CustomPlacesActivity.this, "Perfect", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(CustomPlacesActivity.this,CustomSecondActivity.class);
                    startActivity(intent);
                }
            }
        });


    }




    public boolean isServicesOK(){
        Log.d(TAG,"Checking Google Services Version");
        int availble= GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(CustomPlacesActivity.this);
        if(availble== ConnectionResult.SUCCESS){
            return true;
        }else if(GoogleApiAvailability.getInstance().isUserResolvableError(availble)){
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(this,availble,ERROR_DIALOG_REQUEST);
            dialog.show();
        } else{
            Toast.makeText(this, "No proper Google Play Services Available ", Toast.LENGTH_SHORT).show();

        }
        return false;
    }
}
