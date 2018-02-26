package com.d3mstudio.catalogmovie;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by kfazri75 on 2/17/18.
 */

public class AppPreference {
    private String KEY_UPCOMING = "upcoming";
    private String KEY_DAILY = "daily";
    private String PREF_NAME = "UserPref";

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public AppPreference(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void setUpcoming(boolean status){
        editor.putBoolean(KEY_UPCOMING, status);
        editor.apply();
    }

    public void setDaily(boolean status){
        editor.putBoolean(KEY_DAILY, status);
        editor.apply();
    }

    public boolean isUpcoming(){
        return preferences.getBoolean(KEY_UPCOMING,false);
    }

    public boolean isDaily(){
        return preferences.getBoolean(KEY_DAILY, false);
    }
}
