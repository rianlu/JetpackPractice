package com.example.sharedpreferencesdemo;

import android.content.Context;
import android.content.SharedPreferences;

public class DataUtil {

    private Context context;
    private SharedPreferences sp;

    public DataUtil(Context context) {
        this.context = context;
        sp = context.getSharedPreferences("My_DAT", Context.MODE_PRIVATE);
    }

    public void saveData() {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("NAME", "Kotlin");
        editor.apply();
    }

    public String loadData() {
        String name = sp.getString("NAME", "Android");
        return name;
    }
}
