package com.example.diksha.foodtracker.pojos;

/**
 * Created by jaydeep on 10/9/18.
 */

public class Records {

    public static final String TABLE_NAME_FOOD = "foods";

    public static final String KEY_ID = "id";
    public static final String KEY_FOOD_NAME = "food_name";
    public static final String KEY_FOOD_TYPE = "type";
    public static final String KEY_CARBOHYDRATE = "carbohydrate";
    public static final String KEY_FAT= "fat";
    public static final String KEY_PROTIEN = "protien";
    public static final String KEY_ENERGY = "energy";
    public static final String KEY_DATE = "current_date";
    public static final String KEY_TIME = "current_time";



    private String id,foodName,foodType,carbohydrate,fat,protien, energy, current_date, current_time;
    private boolean isShowDate = false;



    // Create table SQL query
    public static final String CREATE_TABLE_MEALS =
            "CREATE TABLE " + TABLE_NAME_FOOD + "("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + KEY_FOOD_NAME + " TEXT,"
                    + KEY_FOOD_TYPE + " TEXT,"
                    + KEY_CARBOHYDRATE + " TEXT,"
                    + KEY_FAT + " TEXT,"
                    + KEY_PROTIEN + " TEXT,"
                    + KEY_ENERGY + " TEXT,"
                    + KEY_DATE + " TEXT,"
                    + KEY_TIME + " TEXT"
                    + ")";

    public Records(String foodName, String foodType, String carbohydrate,
                   String fat, String protien, String energy, String current_date, String current_time) {
        this.foodName = foodName;
        this.foodType = foodType;
        this.carbohydrate = carbohydrate;
        this.fat = fat;
        this.protien = protien;
        this.energy = energy;
        this.current_date = current_date;
        this.current_time = current_time;
    }

    public Records() {
    }



    public boolean isShowDate() {
        return isShowDate;
    }

    public void setShowDate(boolean showDate) {
        isShowDate = showDate;
    }

    public String getDate1() {
        return current_date;
    }

    public void setDate1(String current_date) {
        this.current_date = current_date;
    }

    public String getCurrent_time() {
        return current_time;
    }

    public void setCurrent_time(String current_time) {
        this.current_time = current_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMealName() {
        return foodName;
    }

    public void setMealName(String mealName) {
        this.foodName = mealName;
    }

    public String getMealCategory() {
        return foodType;
    }

    public void setMealCategory(String foodType) {
        this.foodType = foodType;
    }

    public String getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(String carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getProtien() {
        return protien;
    }

    public void setProtien(String protien) {
        this.protien = protien;
    }

    public String getCalories() {
        return energy;
    }

    public void setCalories(String calories) {
        this.energy = calories;
    }
}
