package com.example.diksha.foodtracker;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diksha.foodtracker.trackerdatabase.FoodTrackerDatabase;
import com.example.diksha.foodtracker.pojos.FoodType;

import java.util.ArrayList;

/**
 * Created by jaydeep on 11/9/18.
 */

public class AddFoodTypeActivity extends AppCompatActivity {


    private AutoCompleteTextView etTypeName;
    private TextView add,show;

    private FoodTrackerDatabase foodTrackerDatabase;
    private SQLiteDatabase sqLiteDatabase;

    private ArrayList<FoodType> foodTypeArrayList =new ArrayList<>();
    private ArrayList<String> categoriStringArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_type);
        initComponent();
        setToolbar();
        foodTrackerDatabase = new FoodTrackerDatabase(AddFoodTypeActivity.this);
        sqLiteDatabase = foodTrackerDatabase.getWritableDatabase();
        foodTypeArrayList = foodTrackerDatabase.getAllcategories();

        for (FoodType foodType : foodTypeArrayList){

            categoriStringArrayList.add(foodType.getCatName());
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, categoriStringArrayList);

        etTypeName.setAdapter(adapter);
        etTypeName.setThreshold(1);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etTypeName.getText().toString().equalsIgnoreCase("") ){

                    Toast.makeText(AddFoodTypeActivity.this, "Please enter food type", Toast.LENGTH_SHORT).show();
                }else {

                    FoodType foodType = new FoodType(etTypeName.getText().toString());

                    foodTrackerDatabase.insertCategory(foodType);
                    etTypeName.setText("");


                }
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(AddFoodTypeActivity.this, UpdateFoodTypeActivity.class);
                startActivity(it);

            }
        });


    }

    private void initComponent() {


        etTypeName = (AutoCompleteTextView) findViewById(R.id.etTypeName);
        add = (TextView) findViewById(R.id.add);
        show= (TextView) findViewById(R.id.show);
        show.setVisibility(View.VISIBLE);
    }


    private void setToolbar(){

       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        TextView tvTitleName = (TextView)toolbar.findViewById(R.id.tvTitleName);
        tvTitleName.setText("Add Food Type");



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
