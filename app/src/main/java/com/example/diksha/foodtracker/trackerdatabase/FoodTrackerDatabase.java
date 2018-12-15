package com.example.diksha.foodtracker.trackerdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.diksha.foodtracker.pojos.FoodType;
import com.example.diksha.foodtracker.pojos.AllLogin;
import com.example.diksha.foodtracker.pojos.Records;

import java.util.ArrayList;

/**
 * Created by jaydeep.rana on 10-09-2018.
 */

public class FoodTrackerDatabase extends SQLiteOpenHelper {

    private Context context;

    public FoodTrackerDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        this.context = context;
    }


    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "foodbloger.db";


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(AllLogin.CREATE_TABLE_LOGIN);
        sqLiteDatabase.execSQL(FoodType.CREATE_TABLE_CATEGORY);
        sqLiteDatabase.execSQL(Records.CREATE_TABLE_MEALS);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + AllLogin.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FoodType.TABLE_NAME_FOOD_TYPE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Records.TABLE_NAME_FOOD);

        // Create tables again
        onCreate(sqLiteDatabase);


    }


    public long insertLoginData(AllLogin allLogin) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(AllLogin.KEY_NAME, allLogin.getName());
        values.put(AllLogin.KEY_EMAIL, allLogin.getEmail());
        values.put(AllLogin.KEY_PASSWORD, allLogin.getPassword());
        values.put(AllLogin.KEY_RETYPE, allLogin.getRetype());
        values.put(AllLogin.KEY_PHONE_NUMBER, allLogin.getPhoneNo());
        values.put(AllLogin.KEY_GENDER, allLogin.getGender());
        values.put(AllLogin.KEY_HEIGHT, allLogin.getHeight());
        values.put(AllLogin.KEY_WEIGHT, allLogin.getWeight());
        values.put(AllLogin.KEY_DOB, allLogin.getDob());
        Toast.makeText(context, "Successfully login", Toast.LENGTH_SHORT).show();

        // insert row
        long id = db.insert(AllLogin.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }


    public long updateLoginData(AllLogin allLogin) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(AllLogin.KEY_NAME, allLogin.getName());
        values.put(AllLogin.KEY_EMAIL, allLogin.getEmail());
        values.put(AllLogin.KEY_PASSWORD, allLogin.getPassword());
        values.put(AllLogin.KEY_RETYPE, allLogin.getRetype());
        values.put(AllLogin.KEY_PHONE_NUMBER, allLogin.getPhoneNo());
        values.put(AllLogin.KEY_GENDER, allLogin.getGender());
        values.put(AllLogin.KEY_HEIGHT, allLogin.getHeight());
        values.put(AllLogin.KEY_WEIGHT, allLogin.getWeight());
        values.put(AllLogin.KEY_DOB, allLogin.getDob());


        // insert row


        Toast.makeText(context, "Successfully updated", Toast.LENGTH_SHORT).show();

        long id = db.update(AllLogin.TABLE_NAME, values, AllLogin.KEY_ID + " = ?",
                new String[]{String.valueOf(allLogin.getId())});

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }


    public void deleteLogin(AllLogin allLogin) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(allLogin.TABLE_NAME, allLogin.KEY_ID + " = ?",
                new String[]{String.valueOf(allLogin.getId())});
        db.close();
    }



    public void insertMeals(Records meals) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Records.KEY_FOOD_NAME, meals.getMealName());
        values.put(Records.KEY_FOOD_TYPE, meals.getMealCategory());
        values.put(Records.KEY_CARBOHYDRATE, meals.getCarbohydrate());
        values.put(Records.KEY_PROTIEN, meals.getProtien());
        values.put(Records.KEY_ENERGY, meals.getCalories());
        values.put(Records.KEY_FAT, meals.getFat());
        values.put(Records.KEY_DATE, meals.getDate1());
        values.put(Records.KEY_TIME, meals.getCurrent_time());



        // insert row
         db.insert(Records.TABLE_NAME_FOOD, null, values);

        Toast.makeText(context, "Successfully inserted meal", Toast.LENGTH_SHORT).show();

        // close db connection
        db.close();

        // return newly inserted row id

    }


    public void updateMeals(Records meals) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Records.KEY_FOOD_NAME, meals.getMealName());
        values.put(Records.KEY_FOOD_TYPE, meals.getMealCategory());
        values.put(Records.KEY_CARBOHYDRATE, meals.getCarbohydrate());
        values.put(Records.KEY_PROTIEN, meals.getProtien());
        values.put(Records.KEY_ENERGY, meals.getCalories());
        values.put(Records.KEY_FAT, meals.getFat());
        values.put(Records.KEY_DATE, meals.getDate1());
        values.put(Records.KEY_TIME, meals.getCurrent_time());



        // insert row
      db.update(Records.TABLE_NAME_FOOD, values, Records.KEY_ID + " = ?",
                new String[]{String.valueOf(meals.getId())});
        Toast.makeText(context, "Successfully updated meal", Toast.LENGTH_SHORT).show();
        // close db connection
        db.close();

        // return newly inserted row id

    }


    public void deleteMeals(Records meals) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(meals.TABLE_NAME_FOOD, meals.KEY_ID + " = ?",
                new String[]{String.valueOf(meals.getId())});
        db.close();
    }

    public void deletecategory(FoodType foodType) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(foodType.TABLE_NAME_FOOD_TYPE, foodType.KEY_ID + " = ?",
                new String[]{String.valueOf(foodType.getId())});
        db.close();
    }



    public void insertCategory(FoodType foodType) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(FoodType.KEY_TYPE_NAME, foodType.getCatName());

        // insert row
        db.insert(FoodType.TABLE_NAME_FOOD_TYPE, null, values);

        Toast.makeText(context, "Successfully inserted category", Toast.LENGTH_SHORT).show();

        // close db connection
        db.close();

        // return newly inserted row id

    }


    public void updateCategory(FoodType foodType) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(FoodType.KEY_TYPE_NAME, foodType.getCatName());

        // insert row
        db.update(FoodType.TABLE_NAME_FOOD_TYPE, values, FoodType.KEY_ID + " = ?",
                new String[]{foodType.getId()});

        Toast.makeText(context, "Successfully updated category", Toast.LENGTH_SHORT).show();
        // close db connection
        db.close();

        // return newly inserted row id

    }





    public boolean isEmailAvailable(String email) {

        boolean isAvailable = false;

        // Select All Query
        String selectQuery = "SELECT  * FROM " + AllLogin.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                if (cursor.getString(cursor.getColumnIndex(AllLogin.KEY_EMAIL)).equalsIgnoreCase(email)){
                    isAvailable = true;
                }

            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return isAvailable;
    }

    public AllLogin getLoginId(String email, String password) {

        AllLogin allLogin = new AllLogin();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + AllLogin.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                if (cursor.getString(cursor.getColumnIndex(AllLogin.KEY_EMAIL)).equalsIgnoreCase(email)){

                    if (cursor.getString(cursor.getColumnIndex(AllLogin.KEY_PASSWORD)).equalsIgnoreCase(password)){
                        allLogin.setId(cursor.getInt(cursor.getColumnIndex(AllLogin.KEY_ID)));
                        allLogin.setName(cursor.getString(cursor.getColumnIndex(AllLogin.KEY_NAME)));
                        allLogin.setEmail(cursor.getString(cursor.getColumnIndex(AllLogin.KEY_EMAIL)));
                        allLogin.setDob(cursor.getString(cursor.getColumnIndex(AllLogin.KEY_DOB)));
                        allLogin.setGender(cursor.getString(cursor.getColumnIndex(AllLogin.KEY_GENDER)));
                        allLogin.setHeight(cursor.getString(cursor.getColumnIndex(AllLogin.KEY_HEIGHT)));
                        allLogin.setWeight(cursor.getString(cursor.getColumnIndex(AllLogin.KEY_WEIGHT)));
                        allLogin.setPassword(cursor.getString(cursor.getColumnIndex(AllLogin.KEY_PASSWORD)));
                        allLogin.setPhoneNo(cursor.getString(cursor.getColumnIndex(AllLogin.KEY_PHONE_NUMBER)));
                        allLogin.setRetype(cursor.getString(cursor.getColumnIndex(AllLogin.KEY_RETYPE)));

                        Toast.makeText(context, "Successfully login", Toast.LENGTH_SHORT).show();
                    }else {

                        Toast.makeText(context, "Password not match", Toast.LENGTH_SHORT).show();
                    }

                }

            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return allLogin;
    }

    public AllLogin getLoginIdForSignuUp(String email, String password) {

        AllLogin allLogin = new AllLogin();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + AllLogin.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                if (cursor.getString(cursor.getColumnIndex(AllLogin.KEY_EMAIL)).equalsIgnoreCase(email)){

                    if (cursor.getString(cursor.getColumnIndex(AllLogin.KEY_PASSWORD)).equalsIgnoreCase(password)){
                        allLogin.setId(cursor.getInt(cursor.getColumnIndex(AllLogin.KEY_ID)));
                        allLogin.setName(cursor.getString(cursor.getColumnIndex(AllLogin.KEY_NAME)));
                        allLogin.setEmail(cursor.getString(cursor.getColumnIndex(AllLogin.KEY_EMAIL)));
                        allLogin.setDob(cursor.getString(cursor.getColumnIndex(AllLogin.KEY_DOB)));
                        allLogin.setGender(cursor.getString(cursor.getColumnIndex(AllLogin.KEY_GENDER)));
                        allLogin.setHeight(cursor.getString(cursor.getColumnIndex(AllLogin.KEY_HEIGHT)));
                        allLogin.setWeight(cursor.getString(cursor.getColumnIndex(AllLogin.KEY_WEIGHT)));
                        allLogin.setPassword(cursor.getString(cursor.getColumnIndex(AllLogin.KEY_PASSWORD)));
                        allLogin.setPhoneNo(cursor.getString(cursor.getColumnIndex(AllLogin.KEY_PHONE_NUMBER)));
                        allLogin.setRetype(cursor.getString(cursor.getColumnIndex(AllLogin.KEY_RETYPE)));


                    }else {


                    }

                }

            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return allLogin;
    }


    public AllLogin getLoginData(String id) {

        AllLogin allLogin = new AllLogin();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + AllLogin.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                if (cursor.getString(cursor.getColumnIndex(AllLogin.KEY_ID)).equalsIgnoreCase(id)){

                        allLogin.setId(cursor.getInt(cursor.getColumnIndex(AllLogin.KEY_ID)));
                        allLogin.setName(cursor.getString(cursor.getColumnIndex(AllLogin.KEY_NAME)));
                        allLogin.setEmail(cursor.getString(cursor.getColumnIndex(AllLogin.KEY_EMAIL)));
                        allLogin.setDob(cursor.getString(cursor.getColumnIndex(AllLogin.KEY_DOB)));
                        allLogin.setGender(cursor.getString(cursor.getColumnIndex(AllLogin.KEY_GENDER)));
                        allLogin.setHeight(cursor.getString(cursor.getColumnIndex(AllLogin.KEY_HEIGHT)));
                        allLogin.setWeight(cursor.getString(cursor.getColumnIndex(AllLogin.KEY_WEIGHT)));
                        allLogin.setPassword(cursor.getString(cursor.getColumnIndex(AllLogin.KEY_PASSWORD)));
                        allLogin.setPhoneNo(cursor.getString(cursor.getColumnIndex(AllLogin.KEY_PHONE_NUMBER)));
                        allLogin.setRetype(cursor.getString(cursor.getColumnIndex(AllLogin.KEY_RETYPE)));

                        Toast.makeText(context, "Successfully login", Toast.LENGTH_SHORT).show();


                }

            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return allLogin;
    }

    public FoodType getCategory(String id) {

        FoodType foodType = new FoodType();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + FoodType.TABLE_NAME_FOOD_TYPE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                if (cursor.getString(cursor.getColumnIndex(AllLogin.KEY_ID)).equalsIgnoreCase(id)){

                    foodType.setId(cursor.getString(cursor.getColumnIndex(foodType.KEY_ID)));
                    foodType.setCatName(cursor.getString(cursor.getColumnIndex(foodType.KEY_TYPE_NAME)));


                }

            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return foodType;
    }



    public ArrayList<FoodType> getAllcategories() {

        ArrayList<FoodType> foodTypeArrayList = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + FoodType.TABLE_NAME_FOOD_TYPE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                FoodType foodType = new FoodType();
                foodType.setId(cursor.getString(cursor.getColumnIndex(FoodType.KEY_ID)));
                foodType.setCatName(cursor.getString(cursor.getColumnIndex(FoodType.KEY_TYPE_NAME)));

                foodTypeArrayList.add(foodType);

            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return foodTypeArrayList;
    }


    public ArrayList<Records> getAllMeals() {

        ArrayList<Records> mealsArrayList = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Records.TABLE_NAME_FOOD;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                Records meals = new Records();
                meals.setId(cursor.getString(cursor.getColumnIndex(Records.KEY_ID)));
                meals.setMealName(cursor.getString(cursor.getColumnIndex(Records.KEY_FOOD_NAME)));
                meals.setCalories(cursor.getString(cursor.getColumnIndex(Records.KEY_ENERGY)));
                meals.setCarbohydrate(cursor.getString(cursor.getColumnIndex(Records.KEY_CARBOHYDRATE)));
                meals.setProtien(cursor.getString(cursor.getColumnIndex(Records.KEY_PROTIEN)));
                meals.setFat(cursor.getString(cursor.getColumnIndex(Records.KEY_FAT)));
                meals.setDate1(cursor.getString(cursor.getColumnIndex(Records.KEY_DATE)));
                meals.setCurrent_time(cursor.getString(cursor.getColumnIndex(Records.KEY_TIME)));
                meals.setMealCategory(cursor.getString(cursor.getColumnIndex(Records.KEY_FOOD_TYPE)));

                mealsArrayList.add(meals);

            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return mealsArrayList;
    }



    public Records getMealById(String id) {

        Records meals = new Records();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Records.TABLE_NAME_FOOD;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(cursor.getColumnIndex(Records.KEY_ID)).equalsIgnoreCase(id)){
                    meals.setId(cursor.getString(cursor.getColumnIndex(Records.KEY_ID)));
                    meals.setMealName(cursor.getString(cursor.getColumnIndex(Records.KEY_FOOD_NAME)));
                    meals.setCalories(cursor.getString(cursor.getColumnIndex(Records.KEY_ENERGY)));
                    meals.setCarbohydrate(cursor.getString(cursor.getColumnIndex(Records.KEY_CARBOHYDRATE)));
                    meals.setProtien(cursor.getString(cursor.getColumnIndex(Records.KEY_PROTIEN)));
                    meals.setFat(cursor.getString(cursor.getColumnIndex(Records.KEY_FAT)));
                    meals.setDate1(cursor.getString(cursor.getColumnIndex(Records.KEY_DATE)));
                    meals.setCurrent_time(cursor.getString(cursor.getColumnIndex(Records.KEY_TIME)));
                    meals.setMealCategory(cursor.getString(cursor.getColumnIndex(Records.KEY_FOOD_TYPE)));
                }



            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return meals;
    }


}
