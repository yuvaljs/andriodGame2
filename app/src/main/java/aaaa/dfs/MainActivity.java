package aaaa.dfs;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class MainActivity extends AppCompatActivity {


    private ExtendedFloatingActionButton start_game_button, table_of_records_button ,sensors_mode_button ,buttons_mode_button;
   // private  ColorStateList buttonClickColor = new ColorStateList(255,0x00,0xBF, 0xA5);
    private boolean buttonsMode ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main1);

        findViews();
        initViews();



















    }

    private void findViews() {
        start_game_button = findViewById(R.id.start_game_button);
        table_of_records_button = findViewById(R.id.table_of_records_button);
        sensors_mode_button = findViewById(R.id.sensors_mode_button);
        buttons_mode_button = findViewById(R.id.buttons_mode_button);

    }
    private void initViews() {

        start_game_button.setOnClickListener(v->setOnStartGameButton());
                table_of_records_button.setOnClickListener(v->setOnTableOfRecordsButton());
        sensors_mode_button.setOnClickListener(v->setOnSensorsModeButton());
                buttons_mode_button.setOnClickListener(v->setOnButtonsModeButton());
        setButtonsMode(getIntent().getBooleanExtra("buttonsMode",true)) ;


    }
    private void setOnStartGameButton() {

        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        intent.putExtra("buttonsMode", buttonsMode);
        startActivity(intent);
        finish();

    }
    private void setOnTableOfRecordsButton() {
        Intent intent = new Intent(MainActivity.this, MainActivityRecords.class);
        intent.putExtra("buttonsMode", buttonsMode);
        startActivity(intent);
        finish();
    }
    public void setOnSensorsModeButton() {

        setButtonsMode(false);

    }
    public void setOnButtonsModeButton() {

       setButtonsMode(true);


    }

    private void setButtonsMode(boolean buttonsMode) {

        if (buttonsMode)
        {
            this.buttonsMode = true;
            sensors_mode_button.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.cyanA400)));
            buttons_mode_button.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.cyanA700)));
        }
        else
        {
            this.buttonsMode = false;
            sensors_mode_button.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.cyanA700)));
            buttons_mode_button.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.cyanA400)));
        }



    }


    private void startGame() {
        Intent intent  = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
        finish();



    }
}