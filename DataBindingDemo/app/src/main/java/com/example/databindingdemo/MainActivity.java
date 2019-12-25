package com.example.databindingdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.databindingdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    //TextView textView;
    //Button button;
    MyViewModel viewModel;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //textView = findViewById(R.id.textView);
        //button = findViewById(R.id.button);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        /*viewModel.getNumber().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.textView.setText(String.valueOf(integer));
            }
        });*/
        /*binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.add();
            }
        });*/


    }
}
