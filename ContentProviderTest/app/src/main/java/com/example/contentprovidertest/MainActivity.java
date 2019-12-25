package com.example.contentprovidertest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MyContentObserver observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);
        Uri uri = Uri.parse("content://com.example.contentprovidertest/user");
        observer = new MyContentObserver(this, new Handler());
        getContentResolver().registerContentObserver(uri, true, observer);

        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
            textView.append(cursor.getString(cursor.getColumnIndex("id")));
            textView.append(cursor.getString(cursor.getColumnIndex("name")));
            textView.append("\n");
        }
        cursor.close();

        ContentValues values = new ContentValues();
        values.put("id", 2);
        values.put("name", "aaa");
        getContentResolver().insert(uri, values);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getContentResolver().unregisterContentObserver(observer);
    }
}
