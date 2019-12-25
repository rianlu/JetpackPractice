package com.example.savedstatedemo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.SavedStateHandle;

public class MyViewModel extends ViewModel {

    private SavedStateHandle handle;

    public MyViewModel(SavedStateHandle handle) {
        this.handle = handle;
    }

    public MutableLiveData<Integer> getNumber() {
        if (!handle.contains(MainActivity.NUM_KEY)) {
            handle.set(MainActivity.NUM_KEY, 0);
        }
        return handle.getLiveData(MainActivity.NUM_KEY);
    }

    public void add() {
        getNumber().setValue(getNumber().getValue() + 1);
    }
}
