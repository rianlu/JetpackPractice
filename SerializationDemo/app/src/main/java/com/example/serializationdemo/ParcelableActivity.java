package com.example.serializationdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ParcelableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcelable);

        TextView textView = findViewById(R.id.textView2);
        Intent intent = getIntent();
        //User user = intent.getParcelableExtra("user");
        Bundle bundle = intent.getBundleExtra("data");
        User user = bundle.getParcelable("user");
        textView.setText(user.toString());
    }
}
