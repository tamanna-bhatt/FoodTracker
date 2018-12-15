package com.example.diksha.foodtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.example.diksha.foodtracker.tackeradapters.AllMenuListAdapter;
import com.example.diksha.foodtracker.pojos.AllMenuList;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GridView list;
    private ArrayList<AllMenuList> allMenuListArrayList = new ArrayList<>();
    private String[] menus ={"ADD FOOD TYPE", "ADD NEW RECORD", "VIEW RECORDS", "DELETE RECORDS", "SHARE HISTORY", "LOGOUT"};
    private Integer[] images = {R.drawable.record,R.drawable.restaurant,R.drawable.viewlist,R.drawable.delete,R.drawable.share,R.drawable.logout};

    private AllMenuListAdapter allMenuListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i=0; i<menus.length ; i++){
            AllMenuList allMenuList = new AllMenuList(images[i], menus[i]);
            allMenuListArrayList.add(allMenuList);
        }

        allMenuListAdapter = new AllMenuListAdapter(MainActivity.this, allMenuListArrayList);


        list= (GridView)findViewById(R.id.list);
        list.setAdapter(allMenuListAdapter);

    }
}
