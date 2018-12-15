package com.example.diksha.foodtracker.pojos;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


/**
 * Created by one on 31/10/15.
 */
public class SharedPrefrences {

    // Set Login Id

    public static void setUserId(String userId, Context ctx) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userId", userId);
        editor.commit();
    }

    public static String getLoginId(Context ctx) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        String loginId = preferences.getString("userId", "");
        return loginId;
    }


    public static void clearLoginId(Context ctx){

        SharedPreferences preferences = ctx.getSharedPreferences("userId", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }


    // Set Login Id

    public static void setName(String name, Context ctx) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name", name);
        editor.commit();
    }

    public static String getname(Context ctx) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        String name = preferences.getString("name", "");
        return name;
    }







}
