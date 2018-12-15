package com.example.diksha.foodtracker.pojos;

/**
 * Created by jaydeep on 10/9/18.
 */

public class FoodType {

    public static final String TABLE_NAME_FOOD_TYPE = "foodType";

    public static final String KEY_ID = "id";
    public static final String KEY_TYPE_NAME = "type_name";

    private String id, catName;

    // Create table SQL query
    public static final String CREATE_TABLE_CATEGORY =
            "CREATE TABLE " + TABLE_NAME_FOOD_TYPE + "("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + KEY_TYPE_NAME + " TEXT"
                    + ")";

    public FoodType() {
    }

    public FoodType(String catName) {
        this.catName = catName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
