package aaaa.dfs;

import android.content.Intent;
import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private ExtendedFloatingActionButton start_game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main1);


        initviews();

                findViews();
        

    }
    private void initviews() {
        start_game = findViewById(R.id.start_game);
    }
    private void findViews() {
        start_game.setOnClickListener(v ->startGame());
    }

    private void startGame() {
        Intent intent  = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);



    }
}