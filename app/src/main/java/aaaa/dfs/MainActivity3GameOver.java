package aaaa.dfs;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.gson.Gson;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import aaaa.dfs.model.Coordinates;
import aaaa.dfs.model.Record;
import aaaa.dfs.model.RecordList;
import aaaa.dfs.utilities.SharedPreferencesManager;

public class MainActivity3GameOver extends AppCompatActivity  {
    private TextView end_game_text;
    private int score;
    private  Location currentLocation;
    private FusedLocationProviderClient fusedLocationProviderClient;

    private RecordList recordList;
    ExtendedFloatingActionButton play_again_button ;
    Coordinates coordinates;


    // Check if the location permissions are already available.

    // Handle the permission request response.


    private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3_game_over);



        findView();
        initView();



    }
    @SuppressLint("NewApi")
    private void updateRecordList() {
        Gson gson = new Gson();


        try {
             recordList = gson.fromJson(SharedPreferencesManager.getInstance().getString("record list"), RecordList.class);
//
        }
        catch (RuntimeException e)
        {


            recordList = new RecordList();
        }


        if (recordList == null)
            recordList = new RecordList();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        recordList.addRecord(new Record(score,dtf.format(now),new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude())));
        SharedPreferencesManager.getInstance().putString("record list",gson.toJson(recordList));

    }

    @SuppressLint("SetTextI18n")
    private void initView() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        score = getIntent().getIntExtra("score",0);
         end_game_text.setText(" Game over\n Final score:"+score);
        play_again_button.setOnClickListener(v->setOnClick());
        getLocation();

    }
    public void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            getLocationPermission();

            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                currentLocation = location;
                updateRecordList();
                play_again_button.setVisibility(View.VISIBLE);

            }
        });

    }
    private void setOnClick() {

        boolean buttonsMode = getIntent().getBooleanExtra("buttonsMode", true);
        Intent intent = new Intent(MainActivity3GameOver.this,MainActivity.class);
        intent.putExtra("buttonsMode", buttonsMode);
        startActivity(intent);
        finish();
    }

    private void findView() {
        end_game_text = findViewById(R.id.end_game_text);
        play_again_button = findViewById(R.id.play_again_button1);





    }

}