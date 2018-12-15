package com.example.diksha.foodtracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diksha.foodtracker.trackerdatabase.FoodTrackerDatabase;
import com.example.diksha.foodtracker.pojos.FoodType;
import com.example.diksha.foodtracker.pojos.Records;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by jaydeep on 11/9/18.
 */

public class AddRecordActivity extends AppCompatActivity {

    private Spinner spType;
    private EditText etcarbohydrate,etProtien,etFat, etEnergy;
    private AutoCompleteTextView etRecordName;
    private TextView add,show;

    private FoodTrackerDatabase foodTrackerDatabase;
    private SQLiteDatabase sqLiteDatabase;
    private ArrayList<FoodType> foodTypeArrayList = new ArrayList<>();
    private ArrayList<Records> mealsArrayList = new ArrayList<>();
    private ArrayAdapter<String> categoryStringArrayAdapter, mealsStrinAdapter ;
    private ArrayList<String> stringArrayList = new ArrayList<>();
    private ArrayList<String> stringMealsArrayList = new ArrayList<>();
    private Calendar c;
    int mYear;
    int mMonth;
    int mDay;
    int mHour;
    int mMin;
    String current_date;
    String current_time, selectedcategory;
    boolean isAvailable = false;
    private ArrayList<Records> mealsFilterArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_record);
        initComponent();
        setToolbar();
        getCurrentDateTime();
        foodTrackerDatabase = new FoodTrackerDatabase(AddRecordActivity.this);
        sqLiteDatabase = foodTrackerDatabase.getWritableDatabase();

        foodTypeArrayList = foodTrackerDatabase.getAllcategories();
        mealsArrayList = foodTrackerDatabase.getAllMeals();

        for (FoodType foodType : foodTypeArrayList){

            stringArrayList.add(foodType.getCatName());

        }

        for (int i=0; i<mealsArrayList.size() ; i++){

            isAvailable = false;

            for (int z=0;z<mealsFilterArrayList.size() ; z++){

                if (mealsFilterArrayList.get(z).getMealName().equalsIgnoreCase(mealsArrayList.get(i).getMealCategory())){
                    isAvailable = true;

                }
            }

            if (isAvailable == false){

                mealsFilterArrayList.add(mealsArrayList.get(i));
                stringMealsArrayList.add(mealsArrayList.get(i).getMealName());
            }

        }


        categoryStringArrayAdapter = new ArrayAdapter<String>(AddRecordActivity.this,
                android.R.layout.simple_list_item_1,stringArrayList);
        spType.setAdapter(categoryStringArrayAdapter);


        mealsStrinAdapter = new ArrayAdapter<String>(AddRecordActivity.this,
               R.layout.item_search_product,R.id.tvName,stringMealsArrayList);
        etRecordName.setAdapter(mealsStrinAdapter);
        etRecordName.setThreshold(1);

        etRecordName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                etFat.setText(mealsFilterArrayList.get(i).getFat());
                etcarbohydrate.setText(mealsFilterArrayList.get(i).getCarbohydrate());
                etEnergy.setText(mealsFilterArrayList.get(i).getCalories());
                etProtien.setText(mealsFilterArrayList.get(i).getProtien());
            }
        });

        spType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                selectedcategory = foodTypeArrayList.get(i).getCatName();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                getCurrentDateTime();

                if (etRecordName.getText().toString().equalsIgnoreCase("") || etcarbohydrate.getText().toString().equalsIgnoreCase("")
                        || etProtien.getText().toString().equalsIgnoreCase("") || etFat.getText().toString().equalsIgnoreCase("")|| etEnergy.getText().toString().equalsIgnoreCase("")){

                    Toast.makeText(AddRecordActivity.this, "Please fill all details", Toast.LENGTH_SHORT).show();
                }else {

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddRecordActivity.this);
                    alertDialogBuilder.setMessage("Are you sure want to add ?");
                    alertDialogBuilder.setPositiveButton("yes",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {

                                    Records meals = new Records(etRecordName.getText().toString(), selectedcategory,etcarbohydrate.getText().toString(),
                                            etProtien.getText().toString(),etFat.getText().toString(), etEnergy.getText().toString(),current_date,current_time);

                                    foodTrackerDatabase.insertMeals(meals);

                                    etRecordName.setText("");
                                    etcarbohydrate.setText("");
                                    etProtien.setText("");
                                    etEnergy.setText("");
                                    etFat.setText("");

                                }
                            });

                    alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            dialogInterface.dismiss();
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();





                }
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(AddRecordActivity.this, AllRecordsActivity.class);
                startActivity(it);

            }
        });


    }

    private void initComponent() {

        spType = (Spinner)findViewById(R.id.spType);
        etRecordName = (AutoCompleteTextView) findViewById(R.id.etRecordName);
        etcarbohydrate = (EditText) findViewById(R.id.etcarbohydrate);
        etProtien = (EditText) findViewById(R.id.etProtien);
        etFat = (EditText) findViewById(R.id.etFat);
        etEnergy = (EditText) findViewById(R.id.etEnergy);
        add = (TextView) findViewById(R.id.add);
        show= (TextView) findViewById(R.id.show);

    }


    private void setToolbar(){

       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        TextView tvTitleName = (TextView)toolbar.findViewById(R.id.tvTitleName);
        tvTitleName.setText("Add Your Food");
        ImageView ivUser = (ImageView)toolbar.findViewById(R.id.ivUser);
        ivUser.setVisibility(View.VISIBLE);

        ivUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(AddRecordActivity.this, UpdateProfileActivity.class);
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


    public void getCurrentDateTime() {

        // Get current date and and time when submit the each answer
        c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH) + 1;
        mDay = c.get(Calendar.DAY_OF_MONTH);

        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMin = c.get(Calendar.MINUTE);


        // Converted into 12 Hrs format
        int hour;
        String am_pm;
        if (mHour > 12) {
            hour = mHour - 12;
            am_pm = "PM";
        } else {
            hour = mHour;
            am_pm = "AM";
        }

        current_date = mDay + "-" + mMonth + "-" + mYear;
        current_time = hour + ":" + mMin + " " + am_pm;

        Log.e("currentDate",current_date);
    }

    @Override
    public void onBackPressed() {

        finish();
    }
}
