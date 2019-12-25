package com.example.viewmodeldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button addBtn;
    MyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        addBtn = findViewById(R.id.button);

        viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        textView.setText(String.valueOf(viewModel.getNum()));

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setNum(viewModel.getNum() + 1);
                textView.setText(String.valueOf(viewModel.getNum()));

            }
        });
    }
}
