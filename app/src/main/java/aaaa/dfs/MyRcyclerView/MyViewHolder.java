package aaaa.dfs.MyRcyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import aaaa.dfs.R;


public class MyViewHolder extends RecyclerView.ViewHolder {

     ImageView main_item_background;
    public TextView main_place_view, main_score_view, text_data;
    public ImageButton main_item_button;

//    public ImageView main_image_view;
//    public TextView main_name;
//
//    public TextView main_email;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        main_item_background = itemView.findViewById(R.id.main_item_background);
        main_score_view = itemView.findViewById(R.id.main_score_view);
        main_place_view = itemView.findViewById(R.id.main_place_view);
        text_data = itemView.findViewById(R.id.text_data);
        main_item_button = itemView.findViewById(R.id.main_item_button);
    }
}
