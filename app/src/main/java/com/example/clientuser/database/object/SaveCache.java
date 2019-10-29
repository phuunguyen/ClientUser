package com.example.clientuser.database.object;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

public class SaveCache {

    public static final String DB_NAME = "III";

    private Context context;

    public SaveCache(Context context) {
        this.context = context;
    }

    public void putBoolean(String name, Boolean data) {
        SharedPreferences shared = context.getSharedPreferences(DB_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putBoolean(name, data);
        editor.commit();
    }

    public boolean getBoolean(String key, boolean defValue) {
        SharedPreferences shared = context.getSharedPreferences(DB_NAME, Activity.MODE_PRIVATE);
        return shared.getBoolean(key, defValue);
    }

    public void putString(String name, String data) {
        SharedPreferences shared = context.getSharedPreferences(DB_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putString(name, data);
        editor.commit();

    }

    public String getString(String key, String defValue) {
        SharedPreferences shared = context
                .getSharedPreferences(DB_NAME, Activity.MODE_PRIVATE);
        return shared.getString(key, defValue);
    }

    public void putInt(String name, int value) {
        SharedPreferences shared = context
                .getSharedPreferences(DB_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putInt(name, value);
        editor.commit();
    }

    public int getInt(String name, int defValue) {
        SharedPreferences shared = context
                .getSharedPreferences(DB_NAME, Activity.MODE_PRIVATE);
        return shared.getInt(name, defValue);

    }

    public void remove(String name) {
        SharedPreferences shared = context
                .getSharedPreferences(DB_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.remove(name);
        editor.commit();
    }

    public void clear(){
        SharedPreferences shared = context
                .getSharedPreferences(DB_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.clear();
        editor.commit();
    }



}
