package aaaa.dfs;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class activityExperiment extends AppCompatActivity {
    int screenWidth,screenHeight,startPositionX , startPositionY ;
    ImageView gameMat[][] = new ImageView[5][3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        DisplayMetrics matrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(matrics);
        screenWidth =  matrics.widthPixels;
        screenHeight = matrics.heightPixels;
        startPositionX = - screenWidth/2 + screenWidth/6;
        startPositionY = - screenHeight/2+ screenHeight/12;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment);


        findView();
        initView();

    }

    private void findView() {

        gameMat[0][0] = findViewById(R.id.view_0_0);
        gameMat[0][1] = findViewById(R.id.view_0_1);
        gameMat[0][2] = findViewById(R.id.view_0_2);
        gameMat[1][0] = findViewById(R.id.view_1_0);
        gameMat[1][1] = findViewById(R.id.view_1_1);
        gameMat[1][2] = findViewById(R.id.view_1_2);
        gameMat[2][0] = findViewById(R.id.view_2_0);
        gameMat[2][1] = findViewById(R.id.view_2_1);
        gameMat[2][2] = findViewById(R.id.view_2_2);
        gameMat[3][0] = findViewById(R.id.view_3_0);
        gameMat[3][1] = findViewById(R.id.view_3_1);
        gameMat[3][2] = findViewById(R.id.view_3_2);
        gameMat[4][0] = findViewById(R.id.view_4_0);
        gameMat[4][1] = findViewById(R.id.view_4_1);
        gameMat[4][2] = findViewById(R.id.view_4_2);




    }

    private  void initView() {
        for(int r = 0; r<gameMat.length ; r++ )
        {
            for(int c = 0; c < gameMat[1].length;c++)
            {

                gameMat[r][c].setScaleX(1/3f);
                gameMat[r][c].setScaleY(1/6f);
                gameMat[r][c].setImageResource(R.drawable.bomb_svgrepo_com);
                gameMat[r][c].setX (startPositionX + screenWidth/3 * c) ;
                gameMat[r][c].setY (startPositionY + screenHeight/6 * r ) ;
            }

        }
    }
}