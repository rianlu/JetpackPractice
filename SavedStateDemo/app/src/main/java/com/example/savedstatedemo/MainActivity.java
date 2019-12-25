package com.example.savedstatedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.savedstatedemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static String NUM_KEY = "num_key";
    ActivityMainBinding binding;
    MyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this, new SavedStateViewModelFactory(getApplication(), this)).get(MyViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
    }
}
