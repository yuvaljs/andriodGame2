package aaaa.dfs;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import aaaa.dfs.fragments.ListFragment;
import aaaa.dfs.model.RecordList;
import aaaa.dfs.myInterfaces.OnClick;
import aaaa.dfs.utilities.SharedPreferencesManager;

public class MainActivityRecords extends AppCompatActivity implements OnMapReadyCallback {
   private FrameLayout main_FRAGMENT_lis;
    SupportMapFragment mapFragment;

    private RecordList recordList;

    private GoogleMap mygoogleMap;



    private ListFragment listFragment ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_records);
        findViews();
        initViews();


    }

    private void initViews() {




        mapFragment.getMapAsync(MainActivityRecords.this);




        recordList = new Gson().fromJson(SharedPreferencesManager.getInstance().getString("record list"), RecordList.class);
        listFragment = new ListFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.main_FRAGMENT_list, listFragment).commit();

        listFragment.setOnClick(new OnClick() {
            @Override
            public void onClick(int number) {
                if (number ==-1)
                {
                    boolean buttonsMode = getIntent().getBooleanExtra("buttonsMode",false);
                    Intent intent = new Intent(MainActivityRecords.this, MainActivity.class);
                    intent.putExtra("buttonsMode", buttonsMode);
                    startActivity(intent);
                    finish();
                }
                else{
                    moveLocation(number);
                }

            }
        });








    }


    public void moveLocation(int num) {

        LatLng latLng = recordList.getRecordList().get(num-1).getLatLng();
        CameraPosition cameraPosition =  new CameraPosition.Builder().target(latLng).zoom(15.0f).build();
        //mygoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mygoogleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        MarkerOptions options = new MarkerOptions().position(latLng);
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mygoogleMap.addMarker(options);





    }




    private void findViews() {
        main_FRAGMENT_lis = findViewById(R.id.main_FRAGMENT_list);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);


    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mygoogleMap = googleMap;
        LatLng latLng = new LatLng(34, 151);
        mygoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }
}