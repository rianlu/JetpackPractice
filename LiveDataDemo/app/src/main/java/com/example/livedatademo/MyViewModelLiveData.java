package com.example.livedatademo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModelLiveData extends ViewModel {

    private MutableLiveData<Integer> number;
    private MutableLiveData<String> str;

    public MutableLiveData<Integer> getNumber() {
        if (number == null) {
            number = new MutableLiveData<>();
            number.setValue(0);
        }
        return number;
    }

    public MutableLiveData<String> getStr() {
        if (str == null) {
            str = new MutableLiveData<>();
        }
        return str;
    }

    public void addNumber(int num) {
        if (number != null) {
            number.setValue(number.getValue() + num);
        }
    }
}
