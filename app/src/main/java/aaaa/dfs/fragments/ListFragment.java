package aaaa.dfs.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import aaaa.dfs.MyRcyclerView.Item;
import aaaa.dfs.MyRcyclerView.MyAdapter;
import aaaa.dfs.R;
import aaaa.dfs.model.Record;
import aaaa.dfs.model.RecordList;
import aaaa.dfs.myInterfaces.OnClick;
import aaaa.dfs.utilities.SharedPreferencesManager;

public class ListFragment extends Fragment {
    //MaterialButton list_LBL_send;
    private ExtendedFloatingActionButton go_back_main_activity_button;
    RecyclerView main_recycler_view;
    private RecordList recordList;
    Gson gson;
    public final int COLRS[] = { R.color.red_100 ,R.color.pink_100 ,R.color.purple_100 ,R.color.deep_purple_100 ,R.color.indigo_100 ,R.color.blue_100 ,R.color.light_blue_100 ,R.color.cyan_100 ,R.color.teal_100 ,R.color.green_100};
    List<Item> items;
    private OnClick onClick = null;
    private OnClick fragmentOnClick = new OnClick() {
        @Override
        public void onClick(int number) {
            if (onClick != null)
                onClick.onClick(number);
        }
    };
    public ListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_list, container, false);
        findView(v);
        initView(v);

        return  v;
    }

    private void initView(View v) {

        go_back_main_activity_button.setOnClickListener(V-> fragmentOnClick.onClick(-1));
        gson = new Gson();

        try {
            recordList = gson.fromJson(SharedPreferencesManager.getInstance().getString("record list"), RecordList.class);

        }
        catch (RuntimeException e)
        {
            recordList = new RecordList();
        }
        if (recordList ==null)
            recordList = new RecordList();


        for(int i = 0 ; i<recordList.getNumOfRecord();i++ )
        {
            Record record =  recordList.getRecordList().get(i);
            items.add(new Item(record.getDate(), COLRS[i],i+1,record.getScore()));
        }
        main_recycler_view.setLayoutManager(new LinearLayoutManager(v.getContext()));
        main_recycler_view.setAdapter(new MyAdapter(v, items, fragmentOnClick));





    }



    public ListFragment setOnClick(OnClick onClick)
    {
        this.onClick = onClick;
        return this;
    }



    private void findView(View v) {
       // list_LBL_send = v.findViewById(R.id.list_LBL_send);
        go_back_main_activity_button = v.findViewById(R.id.go_back_main_activity_button);
        main_recycler_view = v.findViewById(R.id.main_recycler_view);
        items = new ArrayList<>();
    }
//
//<TextView
//    android:layout_width="2dp"
//    android:layout_height="wrap_content"
//    android:background="@color/grey_400"
//            />
}



