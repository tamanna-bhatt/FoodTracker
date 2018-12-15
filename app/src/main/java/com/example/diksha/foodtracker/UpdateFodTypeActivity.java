package com.example.diksha.foodtracker;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diksha.foodtracker.trackerdatabase.FoodTrackerDatabase;
import com.example.diksha.foodtracker.pojos.FoodType;

/**
 * Created by jaydeep on 11/9/18.
 */

public class UpdateFodTypeActivity extends AppCompatActivity {


    private AutoCompleteTextView etTypeName;
    private TextView add;

    private FoodTrackerDatabase foodTrackerDatabase;
    private SQLiteDatabase sqLiteDatabase;

  FoodType foodType;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_type);
        initComponent();
        setToolbar();

        id = getIntent().getStringExtra("id");

        add.setText("UPDATE FOOD TYPE");
        foodTrackerDatabase = new FoodTrackerDatabase(UpdateFodTypeActivity.this);
        sqLiteDatabase = foodTrackerDatabase.getWritableDatabase();
        foodType = foodTrackerDatabase.getCategory(id);

        etTypeName.setText(foodType.getCatName());

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etTypeName.getText().toString().equalsIgnoreCase("") ){

                    Toast.makeText(UpdateFodTypeActivity.this, "Please enter food type", Toast.LENGTH_SHORT).show();
                }else {

                    FoodType foodType = new FoodType(etTypeName.getText().toString());
                    foodType.setId(id);

                    foodTrackerDatabase.updateCategory(foodType);
                    etTypeName.setText("");

               finish();

                }
            }
        });


    }

    private void initComponent() {


        etTypeName = (AutoCompleteTextView) findViewById(R.id.etTypeName);
        add = (TextView) findViewById(R.id.add);

    }


    private void setToolbar(){

       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        TextView tvTitleName = (TextView)toolbar.findViewById(R.id.tvTitleName);
        tvTitleName.setText("Update Food Type");



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
