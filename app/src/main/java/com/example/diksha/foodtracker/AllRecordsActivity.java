package com.example.diksha.foodtracker;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.diksha.foodtracker.tackeradapters.AllRecordsAdapter;
import com.example.diksha.foodtracker.trackerdatabase.FoodTrackerDatabase;
import com.example.diksha.foodtracker.pojos.Records;

import java.util.ArrayList;

/**
 * Created by jaydeep on 11/9/18.
 */

public class AllRecordsActivity extends AppCompatActivity {


    private FoodTrackerDatabase foodTrackerDatabase;
    private SQLiteDatabase sqLiteDatabase;

    private RecyclerView rvAllMeals;

    private ArrayList<Records> mealsArrayList = new ArrayList<>();
    private AllRecordsAdapter allRecordsAdapter;
    String currntDate = "", currentType = "";
    private EditText etSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_record);
        initComponent();
        setToolbar();
        foodTrackerDatabase = new FoodTrackerDatabase(AllRecordsActivity.this);
        sqLiteDatabase = foodTrackerDatabase.getWritableDatabase();

        mealsArrayList = foodTrackerDatabase.getAllMeals();

        for (int i=0; i<mealsArrayList.size() ; i++){

            if (!currntDate.equalsIgnoreCase(mealsArrayList.get(i).getDate1())  ||
                    !currentType.equalsIgnoreCase(mealsArrayList.get(i).getMealCategory())){

                currntDate = mealsArrayList.get(i).getDate1();
                currentType = mealsArrayList.get(i).getMealCategory();

                mealsArrayList.get(i).setShowDate(true);
            }else {

                mealsArrayList.get(i).setShowDate(false);
            }
        }



        allRecordsAdapter = new AllRecordsAdapter(AllRecordsActivity.this, mealsArrayList, "");
        rvAllMeals.setAdapter(allRecordsAdapter);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(final Editable editable) {

                try {
                    if (editable.length() > 0) {

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                // Stuff that updates the UI
                                
                                
                                setData(editable.toString());



                            }
                        });


//                        timer = new Timer();
//                        timer.schedule(new TimerTask() {
//                            @Override
//                            public void run() {
//                                // TODO: do what you need here (refresh list)
//                                // you will probably need to use
//                                // runOnUiThread(Runnable action) for some specific
//                                // actions
//
//                                searchResultAdapter =new SearchRecordsAdapter(AllMealNameActivity.this,
//                                        mealsArrayList,editable.toString());
//                                rvAllMeals.setAdapter(searchResultAdapter);
//
//
//                            }
//
//                        }, DELAY);
                    } else {
                        allRecordsAdapter.setdata(mealsArrayList, "");
                        //  listSearchProducts.setVisibility(View.GONE);

//                        llCancel.setVisibility(View.GONE);

                    }

                } catch (Exception e) {
                }

            }
        });



    }

    private void setData(String filter) {

        ArrayList<Records> filterMealsArrayList = new ArrayList<>();

        for (int i=0; i<mealsArrayList.size() ; i++){

            if (mealsArrayList.get(i).getMealName().toLowerCase().contains(filter.toLowerCase()) || mealsArrayList.get(i).getDate1().contains(filter)){
                filterMealsArrayList.add(mealsArrayList.get(i));

            }
        }

        allRecordsAdapter.setdata(filterMealsArrayList,filter);




    }

    private void initComponent() {


        rvAllMeals = (RecyclerView) findViewById(R.id.rvAllMeals);
        etSearch= (EditText) findViewById(R.id.etSearch);
        etSearch.setVisibility(View.VISIBLE);
        final LinearLayoutManager mLayoutManagerHappyHours1 = new LinearLayoutManager(AllRecordsActivity.this,
                LinearLayoutManager.VERTICAL, false);
        rvAllMeals.setLayoutManager(mLayoutManagerHappyHours1);
        rvAllMeals.setNestedScrollingEnabled(false);
        rvAllMeals.setItemAnimator(new DefaultItemAnimator());
        rvAllMeals.setHasFixedSize(true);

    }


    private void setToolbar(){

       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        TextView tvTitleName = (TextView)toolbar.findViewById(R.id.tvTitleName);
        tvTitleName.setText("All Foods");

        ImageView ivUser = (ImageView)toolbar.findViewById(R.id.ivUser);
        ivUser.setVisibility(View.VISIBLE);

        ivUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(AllRecordsActivity.this, UpdateProfileActivity.class);
                startActivity(it);

            }
        });


        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationIcon(R.drawable.ic_left_arrow_6);

        actionBar.setTitle("");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });




    }
}
