package com.example.sqlitedemo;

import android.content.Context;
import android.view.View;

public class CustomizeView extends View {
    public CustomizeView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
