package com.example.androidviewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

public class MyViewModel extends AndroidViewModel {

    private SavedStateHandle handle;
    private SharedPreferences sp;

    public MyViewModel(@NonNull Application application, SavedStateHandle handle) {
        super(application);
        this.handle = handle;
        sp = application.getSharedPreferences("NUMBER_DATA", Context.MODE_PRIVATE);
    }

    public MutableLiveData<Integer> getNumber() {
        if (!handle.contains("NUMBER")) {
            load();
        }
        return handle.getLiveData("NUMBER");
    }

    public void load() {
        int result = sp.getInt("result", 0);
        handle.set("NUMBER", result);
    }

    public void save() {
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("result", getNumber().getValue());
        editor.apply();
    }

    public void add() {
        getNumber().setValue(getNumber().getValue() + 1);
    }
}
