package com.tiger.curious.guide.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by bkang016 on 9/20/17.
 */

public class PreferenceUtils {
    public final static String PREF_DEFAULT_SERVER = "pref_default_server";
    public final static String PREF_DEFAULT_PORT = "pref_default_port";

    private static SharedPreferences preferences;

    public static void init(Context context) {
        preferences = context
                .getSharedPreferences("data_pref", Context.MODE_PRIVATE);
    }


    public static void putString(String key, String value) {
        preferences.edit().putString(key, value).apply();
    }


    public static String getString(String key, String defaultValue) {
        return preferences.getString(key, defaultValue);
    }



}
