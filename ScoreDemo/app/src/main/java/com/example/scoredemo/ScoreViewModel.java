package com.example.scoredemo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScoreViewModel extends ViewModel {

    //private int ascore;
    //private int bscore;
    private MutableLiveData<Integer> ascore;
    private MutableLiveData<Integer> bscore;
    private int aback;
    private int bback;

    public MutableLiveData<Integer> getAscore() {
        if (ascore == null) {
            ascore = new MutableLiveData<>();
            ascore.setValue(0);
        }
        return ascore;
    }

    public MutableLiveData<Integer> getBscore() {
        if (bscore == null) {
            bscore = new MutableLiveData<>();
            bscore.setValue(0);
        }
        return bscore;
    }

    public void addA(int num) {
        aback = ascore.getValue();
        bback = bscore.getValue();
        ascore.setValue(ascore.getValue() + num);
    }

    public void addB(int num) {
        aback = ascore.getValue();
        bback = bscore.getValue();
        bscore.setValue(bscore.getValue() + num);
    }

    public void reset() {
        aback = ascore.getValue();
        bback = bscore.getValue();
        ascore.setValue(0);
        bscore.setValue(0);
    }

    public void undo() {
        ascore.setValue(aback);
        bscore.setValue(bback);
    }
}
