package aaaa.dfs;

import android.util.Log;
import android.widget.ImageView;

public class Player {
int index;

   public ImageView view;

    public void moveRight()
    {
        if (index == 2)//jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj
            index = 0;
        else index++;
    }
    public void moveLeft()
    {

        if (index == 0)
            index = 2;
        else index--;
    }
    public void print()
    {
        Log.d("iugu","kgbhj");
    }




}
