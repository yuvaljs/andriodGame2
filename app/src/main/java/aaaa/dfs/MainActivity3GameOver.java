package aaaa.dfs;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3GameOver extends AppCompatActivity {
    private TextView end_game_text;
    Button play_again_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3_game_over);



                findView();
        initView();

    }

    @SuppressLint("SetTextI18n")
    private void initView() {
        int score = getIntent().getIntExtra("score",0);
        if (score<100)
            end_game_text.setText(" Game over\n Final score:"+score);
        else
            end_game_text.setText(" Winner\n Final score:"+ score);

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