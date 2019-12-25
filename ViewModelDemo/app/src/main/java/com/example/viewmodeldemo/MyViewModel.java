package com.example.viewmodeldemo;

import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    private int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
