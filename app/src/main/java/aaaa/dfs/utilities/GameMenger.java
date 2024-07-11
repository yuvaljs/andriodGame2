package aaaa.dfs.utilities;


import android.util.Log;

import java.util.Random;

public class GameMenger {

    final public int ROW = 12,COL = 5,START_NUMBER_OF_LIVES = 3,PLAYER_START_POSITION = 0,PLAYER_Y_POSITION = 8 ,FRAMES_FOR_BOMB = 3,FRAMES_FOR_COIN = 4 ;//,BEST_SCORE = 100;
    private int playerPosition = PLAYER_START_POSITION,  numOfLives = START_NUMBER_OF_LIVES, score = 0;

    private Random random;




   private boolean needToAddBomb = true, gameStatus = true;

    public int getScore() {
        return score;
    }

   private boolean gameMatBomb[][] = new boolean[ROW][COL];
    private boolean gameMatCoin[][] = new boolean[ROW][COL];


    public boolean[][] getGameMatCoin() {
        return gameMatCoin;
    }

    public boolean[][] getGameMatBomb()  {
        return gameMatBomb;
    }

    public int getNumOfLives() {
        return numOfLives;
    }

    public boolean getGameStatus() {
        return gameStatus;
    }

public GameMenger() {
    for (int r = 0; r < ROW; r++) {
        for (int c = 0; c < COL; c++) {
            gameMatBomb[r][c] = false;
            gameMatCoin[r][c] = false;
        }
    }
    random = new Random();
}

    private void updateLivesAndScore()
    {
        if ( gameMatBomb[PLAYER_Y_POSITION][playerPosition]) {
            gameMatBomb[PLAYER_Y_POSITION][playerPosition] = false;
            numOfLives--;
        }
        if ( gameMatCoin[PLAYER_Y_POSITION][playerPosition]) {
            gameMatCoin[PLAYER_Y_POSITION][playerPosition] = false;
            score++;
        }
        if (numOfLives == 0)
            gameStatus = false;
    }

    public void updateGame() {

//        for(int i = 0 ; i < 100; i++)
//        {
//            Log.d("i","i = "+i);
//        }

        for (int r = ROW -1; r > 0; r--) {
            for (int c = 0; c < COL; c++) {
                gameMatBomb[r][c] = gameMatBomb[r - 1][c];
                gameMatCoin[r][c] = gameMatCoin[r - 1][c];
            }
        }
        for(int i = 0;i<COL;i++)
        {
            gameMatBomb[0][i] = false;
            gameMatCoin[0][i] = false;
        }
        int x = random.nextInt(COL * FRAMES_FOR_BOMB);
        if (x % FRAMES_FOR_BOMB == 0) {
            gameMatBomb[0][x % COL] = true;
        }

        x = random.nextInt(COL * FRAMES_FOR_COIN);
        if (x % FRAMES_FOR_COIN == 0) {
            gameMatCoin[0][x % COL] = !gameMatBomb[0][x % COL];
        }
        updateLivesAndScore();


    }

    public int move(int position)
    {
        if((position <playerPosition)&&(playerPosition > 0))
            moveLeft();
        else if((position >playerPosition)&&(playerPosition < COL-1))
            moveRight();
//        if (playerPosition < 0) playerPosition = 0;
//        else if (playerPosition > COL - 1) playerPosition = COL - 1;
//
//       // Log.d("playerPosition", " playerPosition = "+ playerPosition);
//
//        this.playerPosition = playerPosition;
//       // Log.d("playerPosition", "this.playerPosition = "+ this.playerPosition+", playerPosition = "+ playerPosition);
//        updateLivesAndScore();

        return  this.playerPosition;
    }


    public int moveRight()
    {
        if (playerPosition == COL-1)
            playerPosition = 0;
        else playerPosition++;

        updateLivesAndScore();
        return playerPosition;
    }
    public int moveLeft()
    {

        if (playerPosition == 0)
            playerPosition = COL-1;
        else playerPosition--;

        updateLivesAndScore();
        return playerPosition;
    }













}
