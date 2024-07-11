package aaaa.dfs.MyRcyclerView;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;



import java.util.List;

import aaaa.dfs.R;
import aaaa.dfs.myInterfaces.OnClick;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    View view;
    OnClick onClick = null;
    List<Item> items;

    public MyAdapter(View view, List<Item> items,OnClick onClick ) {
        this.view = view;
        this.items = items;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(view.getContext()).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.text_data.setText(items.get(position).getDate());
        holder.main_score_view.setText("score:"+items.get(position).getScore());
        holder.main_place_view.setText("Place: "+items.get(position).getPlace());
        holder.main_item_background.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(), items.get(position).getBackground())));
        holder.main_item_button.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(), items.get(position).getBackground())));
        holder.main_item_button.setOnClickListener(view->onClick.onClick(position+1));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
