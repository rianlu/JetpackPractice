package com.example.sharedpreferencesdemo;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    String TAG = "SharedPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataUtil dataUtil = new DataUtil(getApplicationContext());
        dataUtil.saveData();
        String name = dataUtil.loadData();
        Log.d(TAG, "onCreate: " + name);
    }
}
