package aaaa.dfs;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;


public class MainActivity3GameOver extends AppCompatActivity {
    private TextView end_game_text;
    Button play_again_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        Log.d("stopTiffffffffffffffffffffffffffffffffffffmer", "stopTimer: Timegfgfgfgfggggggggggggggggggggggggggr Stopped");
        setContentView(R.layout.activity_main3_game_over);
        //Game over\n Final score:100


        //EdgeToEdge.enable(this);


                findView();
        initView();

    }

    @SuppressLint("SetTextI18n")
    private void initView() {
        int score = getIntent().getIntExtra("score",0);
        if (score<100)
        {
            end_game_text.setText("Game over\n Final score:"+score);
        }else end_game_text.setText("Winner\n Final score:100");

        play_again_button.setOnClickListener(v->setOnClick());

    }

    private void setOnClick() {
        finish();
    }

    private void findView() {
        end_game_text = findViewById(R.id.end_game_text);
        play_again_button = findViewById(R.id.play_again_button);





    }
}