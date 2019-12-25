package com.example.sqlitedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "SQLiteDatabase";
    private MySQLiteOpenHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new MySQLiteOpenHelper(this, "test_db", null, 1);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("user", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                Log.d(TAG, "onCreate: " + name);
            } while (cursor.moveToNext());
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
        } else {
            call();
        }
    }

    private void call() {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:10086"));
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1:
                StringBuilder builder = new StringBuilder();
                if (grantResults.length > 0) {
                    for (int a : grantResults) {
                        builder.append(a);
                    }
                    Log.d(TAG, "onRequestPermissionsResult: " + builder.toString());
                }
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call();
                } else {
                    Toast.makeText(this, "你拒绝了权限", Toast.LENGTH_SHORT).show();
                }
        }
    }
}
