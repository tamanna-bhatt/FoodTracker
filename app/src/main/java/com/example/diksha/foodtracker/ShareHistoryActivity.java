package com.example.diksha.foodtracker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.diksha.foodtracker.trackerdatabase.FoodTrackerDatabase;

import java.io.File;
import java.io.FileWriter;

public class ShareHistoryActivity extends AppCompatActivity {

    private LinearLayout gmail,whatsapp,drive,more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_history);

        setToolbar();
        gmail = (LinearLayout)findViewById(R.id.gmail);
        whatsapp = (LinearLayout)findViewById(R.id.whatsapp);
        drive = (LinearLayout)findViewById(R.id.drive);
        more = (LinearLayout)findViewById(R.id.more);


        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exportDB();

                ReferFriend("com.google.android.gm");
            }
        });


        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exportDB();

                ReferFriend("com.whatsapp");
            }
        });


        drive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exportDB();
                ReferFriend("com.google.android.apps.docs");


            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exportDB();

                ReferFriend("");
            }
        });




    }


    private void ReferFriend(String package1){

        Intent shareIntent =
                new Intent(Intent.ACTION_SEND);

        //set the type
        shareIntent.setType("application/csv");

        if (!package1.equalsIgnoreCase("")){
            shareIntent.setPackage(package1);
        }


        //add a subject
        shareIntent.putExtra(Intent.EXTRA_SUBJECT,
                "FOOD TRACKER ");


        //build the body of the message to be shared
        String shareMessage = "Food tracker doc file will send u after some time";


        String csv = (Environment.getExternalStorageDirectory() +  "/" + "FOODTRACKER" + "/" + "foodtracker.csv"); // Here csv file name is MyCsvFile.csv

        //add the message
        shareIntent.putExtra(Intent.EXTRA_TEXT,
                shareMessage);



        File file = new File(csv);
        Uri uri = Uri.fromFile(file);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);

        //start the chooser for sharing
        startActivity(Intent.createChooser(shareIntent,
                "Food Bloger"));
    }




    private void exportDB() {

        FoodTrackerDatabase dbhelper = new FoodTrackerDatabase(ShareHistoryActivity.this);
        File exportDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "FOODTRACKER" + "/", "");
        if (!exportDir.exists())
        {
            exportDir.mkdirs();
        }

        File file = new File(exportDir, "foodtracker.csv");
        try
        {
            file.createNewFile();
            CSVFileWriter csvWrite = new CSVFileWriter(new FileWriter(file));
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            Cursor curCSV = db.rawQuery("SELECT * FROM meals",null);
            csvWrite.writeNext(curCSV.getColumnNames());
            while(curCSV.moveToNext())
            {
//Which column you want to exprort
                String arrStr[] ={curCSV.getString(0),curCSV.getString(1),
                        curCSV.getString(2), curCSV.getString(3), curCSV.getString(4)
                        , curCSV.getString(5), curCSV.getString(6), curCSV.getString(7)};
                csvWrite.writeNext(arrStr);
            }
            csvWrite.close();
            curCSV.close();
        }
        catch(Exception sqlEx)
        {
            Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
        }
    }


    private void setToolbar(){

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        TextView tvTitleName = (TextView)toolbar.findViewById(R.id.tvTitleName);
        tvTitleName.setText("Share records");



        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationIcon(R.drawable.ic_left_arrow_6);

        actionBar.setTitle("");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });

    }

}
