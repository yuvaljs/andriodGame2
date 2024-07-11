package aaaa.dfs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;

import android.util.DisplayMetrics;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;

import aaaa.dfs.myInterfaces.MoveCallback;
import aaaa.dfs.utilities.GameMenger;
import aaaa.dfs.utilities.PositionDetector;


public class MainActivity2 extends AppCompatActivity {
    private GameMenger gameMenger;

    private AppCompatImageButton buttonRight, buttonLeft;

    private TextView text_score;
    private ImageView playerView;
    final public int ROW = 12, COL = 5, START_NUMBER_OF_LIVES = 3, PLAYER_Y_POSITION = 8;
    private int delay = 150;

    private boolean buttonsMode;

    private boolean updateGame = true;

    private int bombId = R.drawable.bomb_svgrepo_com;

    private int coinId = R.drawable.coin;

    private PositionDetector positionDetector;

    private int screenWidth, screenHeight, startPositionX, startPositionY;
    private ImageView[][] gameMat = new ImageView[ROW][COL];
    private final int viewId[][] = {
            {R.id.view_0_0, R.id.view_0_1, R.id.view_0_2, R.id.view_0_3, R.id.view_0_4}
            , {R.id.view_1_0, R.id.view_1_1, R.id.view_1_2, R.id.view_1_3, R.id.view_1_4}
            , {R.id.view_2_0, R.id.view_2_1, R.id.view_2_2, R.id.view_2_3, R.id.view_2_4}
            , {R.id.view_3_0, R.id.view_3_1, R.id.view_3_2, R.id.view_3_3, R.id.view_3_4}
            , {R.id.view_4_0, R.id.view_4_1, R.id.view_4_2, R.id.view_4_3, R.id.view_4_4}
            , {R.id.view_5_0, R.id.view_5_1, R.id.view_5_2, R.id.view_5_3, R.id.view_5_4}
            , {R.id.view_6_0, R.id.view_6_1, R.id.view_6_2, R.id.view_6_3, R.id.view_6_4}
            , {R.id.view_7_0, R.id.view_7_1, R.id.view_7_2, R.id.view_7_3, R.id.view_7_4}
            , {R.id.view_8_0, R.id.view_8_1, R.id.view_8_2, R.id.view_8_3, R.id.view_8_4}
            , {R.id.view_9_0, R.id.view_9_1, R.id.view_9_2, R.id.view_9_3, R.id.view_9_4}
            , {R.id.view_10_0, R.id.view_10_1, R.id.view_10_2, R.id.view_10_3, R.id.view_10_4}
            , {R.id.view_11_0, R.id.view_11_1, R.id.view_11_2, R.id.view_11_3, R.id.view_11_4}
    };

    private LinearLayoutCompat linear_layout_compat_heart;
    private AppCompatImageView[] heartsArr;

    final public Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            gameMenger.updateGame();
            updateGraphics();
            if(gameMenger.getGameStatus())
            {
                delay = Math.max(delay - delay / 150,50);
                handler.postDelayed(this, delay);
            }
            else {
                if (!buttonsMode) positionDetector.stop();

                int score = gameMenger.getScore();
                Intent intent = new Intent(MainActivity2.this, MainActivity3GameOver.class);
                intent.putExtra("score", score);
                intent.putExtra("buttonsMode", buttonsMode);
                startActivity(intent);
                finish();
            }

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
//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        positionDetector.stop();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        positionDetector.start();
//    }


    private void updateGraphics() {
        boolean[][] GameMatBomb = gameMenger.getGameMatBomb();
        boolean[][] GameMatCoin = gameMenger.getGameMatCoin();
        for (int r = 0; r < ROW; r++)
            for (int c = 0; c < COL; c++) {
                if (GameMatBomb[r][c]) {
                    gameMat[r][c].setVisibility(View.VISIBLE);
                    gameMat[r][c].setImageResource(bombId);
                    }
                else if (GameMatCoin[r][c]) {
                    gameMat[r][c].setVisibility(View.VISIBLE);
                    gameMat[r][c].setImageResource(coinId);

                } else {
                    gameMat[r][c].setVisibility(View.INVISIBLE);

                }


            }

        int numOfLives = gameMenger.getNumOfLives();
        for (int i = numOfLives; i < START_NUMBER_OF_LIVES; i++) {
            heartsArr[i].setVisibility(View.INVISIBLE);
        }

        text_score.setText("score:" + gameMenger.getScore());

    }

    private void initviews() {

        startPositionX = -screenWidth / 2 + (screenWidth / COL / 2);
        startPositionY = -screenHeight / 2 + (screenHeight / (ROW) / 2);
        buttonsMode = getIntent().getBooleanExtra("buttonsMode", true);

        if (buttonsMode)
            initButtons();
        else {
            initSensors();
        }

        initPlayer();
        initGameMat();
        initHeartsArr();


        text_score.setText("score:" + 0);
    }

    private void initHeartsArr() {
        linear_layout_compat_heart.setScaleX(1 / (float) COL * 2);
        linear_layout_compat_heart.setScaleY(1 / (float) (ROW) * 2);
        linear_layout_compat_heart.setX(-screenWidth / 2f + (screenWidth / (float) COL) + screenWidth / (float) COL * (COL - 2));
        linear_layout_compat_heart.setY(-screenHeight / 2f + (screenHeight / (float) ROW) * 3 / 4f);

    }

    private void initGameMat() {
        for (int r = 0; r < ROW; r++) {
            for (int c = 0; c < COL; c++) {
                gameMat[r][c].setScaleX(1 / (float) COL/2);
                gameMat[r][c].setScaleY(1 / (float) (ROW));
                gameMat[r][c].setX(startPositionX + screenWidth / (float) COL * c);
                gameMat[r][c].setY((int) (startPositionY + (screenHeight / ROW * r)));

            }
        }
    }

    private void initPlayer() {
        playerView.setScaleX(1 / (float) COL / 2.0f);
        playerView.setScaleY(1 / (float) (ROW));
        playerView.setY(startPositionY + (screenHeight / (float) (ROW)) * (PLAYER_Y_POSITION));
        updatePlayerPosition(gameMenger.PLAYER_START_POSITION);

    }

    private void initSensors() {
        buttonRight.setVisibility(View.GONE);
        buttonLeft.setVisibility(View.GONE);
        positionDetector = new PositionDetector(this, new MoveCallback() {
            @Override
            public void move(int position) {
                position += COL / 2;
                updatePlayerPosition(gameMenger.move(position ));
            }
        });
        positionDetector.start();
    }



    private void initButtons() {

        buttonRight.setScaleX(1 / (float) (COL) * 2);
        buttonRight.setScaleY(1 / (float) (ROW) * 2);
        buttonRight.setX(-screenWidth / 2 + screenWidth / COL + (screenWidth / (float) (COL) * (COL - 2.5f)));
        buttonRight.setY(-screenHeight / 2 + screenHeight / ROW + (screenHeight / (float) (ROW) * (ROW - 2)));
        buttonLeft.setScaleX(1 / (float) (COL) * 2);
        buttonLeft.setScaleY(1 / (float) (ROW) * 2);
        buttonLeft.setX(-screenWidth / 2 + screenWidth / COL + screenWidth / (float) (COL) * 0.5f);
        buttonLeft.setY(-screenHeight / 2 + screenHeight / ROW + screenHeight / (float) (ROW) * (ROW - 2));
        buttonRight.setOnClickListener(v -> moveRight());
        buttonLeft.setOnClickListener(v -> moveLeft());

    }

    private void findViews() {
        buttonRight = findViewById(R.id.main_button_right);
        buttonLeft = findViewById(R.id.main_button_left);
        playerView = findViewById(R.id.view);

        for (int r = 0; r < ROW; r++)
            for (int c = 0; c < COL; c++) {
                gameMat[r][c] = findViewById(viewId[r][c]);
            }

        linear_layout_compat_heart = findViewById(R.id.linear_layout_compat_heart);
        heartsArr = new AppCompatImageView[]{findViewById(R.id.main_IMG_heart1), findViewById(R.id.main_IMG_heart2), findViewById(R.id.main_IMG_heart3)};
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

