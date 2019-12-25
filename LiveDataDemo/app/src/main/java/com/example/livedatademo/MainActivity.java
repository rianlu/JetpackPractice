package com.example.livedatademo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ImageButton dislikeBtn, likeBtn;
    MyViewModelLiveData viewModelLiveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        dislikeBtn = findViewById(R.id.imageButton);
        likeBtn = findViewById(R.id.imageButton3);

        viewModelLiveData = ViewModelProviders.of(this).get(MyViewModelLiveData.class);
        viewModelLiveData.getNumber().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == null) {
                    integer = 0;
                }
                textView.setText(String.valueOf(integer));
            }
        });

        viewModelLiveData.getStr().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);
            }
        });

        dislikeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModelLiveData.addNumber(-1);
            }
        });

        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModelLiveData.addNumber(1);
            }
        });
    }
}
