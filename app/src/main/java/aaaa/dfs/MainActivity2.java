package aaaa.dfs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.util.DisplayMetrics;

//import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
//import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;

public class MainActivity2 extends AppCompatActivity {
    private GameMenger gameMenger;

    private AppCompatImageButton buttonRight, buttonLeft;

    private TextView text_score;
    private ImageView playerView;
    final public int ROW = 6, COL = 4, DELAY = 170, START_NUMBER_OF_LIVES = 3;

    private int screenWidth, screenHeight, startPositionX, startPositionY;
    private ImageView[][] gameMat = new ImageView[ROW][COL];

    private LinearLayoutCompat linear_layout_compat_heart;
    private AppCompatImageView[] heartsArr;

    final public Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            gameMenger.updateGame();
            update_graphics();
            if (gameMenger.getGameStatus()) handler.postDelayed(this, DELAY);
        }
    };


    @SuppressLint("MissingInflatedId")

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        DisplayMetrics metrics = new DisplayMetrics();   //Calculate the screen proportions
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;

        gameMenger = new GameMenger();
        findViews();
        initviews();

        handler.post(runnable);
    }


    private void update_graphics() {
        boolean[][] mengerMat = gameMenger.gameMat;
        for (int r = 0; r < ROW; r++) {
            for (int c = 0; c < COL; c++) {
                gameMat[r][c].setVisibility(mengerMat[r][c] ? View.VISIBLE : View.INVISIBLE);
            }
        }
        int numOfLives = gameMenger.getNumOfLives();
        for (int i = numOfLives; i < START_NUMBER_OF_LIVES; i++) {
            heartsArr[i].setVisibility(View.INVISIBLE);
        }

        text_score.setText("score:" + gameMenger.getScore());

        if (!gameMenger.getGameStatus()) {

            int score = gameMenger.getScore();


            Intent intent = new Intent(MainActivity2.this, MainActivity3GameOver.class);
            intent.putExtra("score", score);
            startActivity(intent);
            finish();


        }


    }

    private void initviews() {
        startPositionX = -screenWidth / 2 + (screenWidth / COL / 2);
        startPositionY = -screenHeight / 2 + (screenHeight / (ROW + 1) / 2);


        buttonRight.setScaleX(1 / (float) (COL));
        buttonRight.setScaleY(1 / (float) (ROW + 1));
        buttonRight.setX(startPositionX + (screenWidth / (float) (COL) * (COL - 1.5f)));
        buttonRight.setY(startPositionY + (screenHeight / (float) (ROW + 1) * (ROW)));

        buttonLeft.setScaleX(1 / (float) (COL));
        buttonLeft.setScaleY(1 / (float) (ROW + 1));


        buttonLeft.setX(startPositionX + screenWidth / (float) (COL) * 0.5f);
        buttonLeft.setY(startPositionY + screenHeight / (float) (ROW + 1) * (ROW));


        buttonRight.setOnClickListener(v -> moveRight());
        buttonLeft.setOnClickListener(v -> moveLeft());


        playerView.setScaleX(1 / (float) COL);
        playerView.setScaleY(1 / (float) (ROW + 1));

        playerView.setY(startPositionY + (screenHeight / (float) (ROW + 1)) * (ROW - 1));
        updatePlayerPosition(gameMenger.PLAYER_START_POSITION);


        for (int r = 0; r < ROW; r++) {
            for (int c = 0; c < COL; c++) {

                gameMat[r][c].setScaleX(1 / (float) COL);
                gameMat[r][c].setScaleY(1 / (float) (ROW + 1));
                gameMat[r][c].setImageResource(R.drawable.bomb_svgrepo_com);
                gameMat[r][c].setX(startPositionX + (screenWidth / (float) COL) * c);
                gameMat[r][c].setY(startPositionY + (screenHeight / (float) (ROW + 1)) * r);
            }

        }
        linear_layout_compat_heart.setScaleX(1 / (float) COL * 2);
        linear_layout_compat_heart.setScaleY(1 / (float) (ROW + 1) * 2);
        linear_layout_compat_heart.setX(-screenWidth / 2f + (screenWidth / (float) COL) + screenWidth / (float) COL * (COL - 2));//+ screenWidth/(float)COL*(COL-1));
        linear_layout_compat_heart.setY(-screenHeight / 2f + (screenHeight / (float) ROW) * 3 / 4f);


        text_score.setText("score:" + 0);


    }

    private void findViews() {
        buttonRight = findViewById(R.id.main_button_right);
        buttonLeft = findViewById(R.id.main_button_left);
        playerView = findViewById(R.id.view);


        gameMat[0][0] = findViewById(R.id.view_0_0);
        gameMat[0][1] = findViewById(R.id.view_0_1);
        gameMat[0][2] = findViewById(R.id.view_0_2);
        gameMat[0][3] = findViewById(R.id.view_0_3);

        gameMat[1][0] = findViewById(R.id.view_1_0);
        gameMat[1][1] = findViewById(R.id.view_1_1);
        gameMat[1][2] = findViewById(R.id.view_1_2);
        gameMat[1][3] = findViewById(R.id.view_1_3);

        gameMat[2][0] = findViewById(R.id.view_2_0);
        gameMat[2][1] = findViewById(R.id.view_2_1);
        gameMat[2][2] = findViewById(R.id.view_2_2);
        gameMat[2][3] = findViewById(R.id.view_2_3);

        gameMat[3][0] = findViewById(R.id.view_3_0);
        gameMat[3][1] = findViewById(R.id.view_3_1);
        gameMat[3][2] = findViewById(R.id.view_3_2);
        gameMat[3][3] = findViewById(R.id.view_3_3);

        gameMat[4][0] = findViewById(R.id.view_4_0);
        gameMat[4][1] = findViewById(R.id.view_4_1);
        gameMat[4][2] = findViewById(R.id.view_4_2);
        gameMat[4][3] = findViewById(R.id.view_4_3);

        gameMat[5][0] = findViewById(R.id.view_5_0);
        gameMat[5][1] = findViewById(R.id.view_5_1);
        gameMat[5][2] = findViewById(R.id.view_5_2);
        gameMat[5][3] = findViewById(R.id.view_5_3);


        linear_layout_compat_heart = findViewById(R.id.linear_layout_compat_heart);
        heartsArr = new AppCompatImageView[]{findViewById(R.id.main_IMG_heart1), findViewById(R.id.main_IMG_heart2), findViewById(R.id.main_IMG_heart3),};
        text_score = findViewById(R.id.text_score);
    }

    private void updatePlayerPosition(int i) {
        playerView.setX(startPositionX + (screenWidth / (float) COL) * i);
    }

    private void moveRight() {
        updatePlayerPosition(gameMenger.moveRight());


    }

    private void moveLeft() {
        updatePlayerPosition(gameMenger.moveLeft());

    }
}

