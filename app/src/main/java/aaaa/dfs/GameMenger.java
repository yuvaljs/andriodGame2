package aaaa.dfs;


public class GameMenger {

    final public int ROW = 6,COL = 4,START_NUMBER_OF_LIVES = 3,PLAYER_START_POSITION = 0,BEST_SCORE = 100;
    private int playerPosition = PLAYER_START_POSITION,  numOfLives = START_NUMBER_OF_LIVES, score = 0;




    boolean needToAddBomb = true, gameStatus = true;

    public int getScore() {
        return score;
    }

    boolean gameMat[][] = new boolean[ROW][COL];



    public boolean[][] getGameMat()  {
        return gameMat;
    }

    public int getNumOfLives() {
        return numOfLives;
    }

    public boolean getGameStatus() {
        return gameStatus;
    }
//final public int DELAY = 100;
public GameMenger() {
    for (int r = 0; r < gameMat.length; r++) {
        for (int c = 0; c < gameMat[1].length; c++) {
            gameMat[r][c] = false;
        }
    }
}
    public void updateGame() {
        score++;
        for (int r = ROW-1; r > 0; r--) {
            for (int c = 0; c < gameMat[1].length; c++) {
                gameMat[r][c] = gameMat[r-1][c];
            }
        }

        int i = COL;
        if(needToAddBomb&&score <= BEST_SCORE - ROW) {
            needToAddBomb = false;
            i =  (int)(Math.random() * COL);
        }else needToAddBomb = true;

        for (int c = 0; c < gameMat[0].length; c++)
            {
                gameMat[0][c] = c==i;
            }
        if ( gameMat[ROW-1][playerPosition]) {
            gameMat[ROW - 1][playerPosition] = false;
            numOfLives--;
        }
        if (numOfLives == 0||score>=BEST_SCORE)
            gameStatus = false;



    }
    public int moveRight()
    {
        if (playerPosition == COL-1)
            playerPosition = 0;
        else playerPosition++;

        if ( gameMat[ROW-1][playerPosition]) {
            gameMat[ROW - 1][playerPosition] = false;
            numOfLives--;
        }
        return playerPosition;
    }
    public int moveLeft()
    {

        if (playerPosition == 0)
            playerPosition = COL-1;
        else playerPosition--;

        if ( gameMat[ROW-1][playerPosition]) {
            gameMat[ROW - 1][playerPosition] = false;
            numOfLives--;
        }
        return playerPosition;
    }













}
