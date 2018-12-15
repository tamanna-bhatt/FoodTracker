package com.example.diksha.foodtracker;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

/**
 * Created by foram on 28/2/17.
 */

public class AppHelper {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final boolean DEBUG_MODE = true;
    private static final String TAG = "[FOODTRACKER]";
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
           };


    public static boolean verifyStoragePermissions(Activity activity) {

        boolean givePermission = false;

        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
            givePermission = false;
        } else {
            givePermission = true;
        }
        return givePermission;
    }

}
