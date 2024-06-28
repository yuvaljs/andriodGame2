package aaaa.dfs;

import android.widget.ImageView;

public class Player {
int index;

   public ImageView view;

    public void moveRight()
    {
        if (index == 2)
            index = 0;
        else index++;
    }
    public void moveLeft()
    {

        if (index == 0)
            index = 2;
        else index--;
    }




}
