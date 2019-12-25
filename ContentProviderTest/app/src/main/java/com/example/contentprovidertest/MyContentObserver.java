package com.example.contentprovidertest;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.widget.Toast;

public class MyContentObserver extends ContentObserver {
    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    private Context context;

    public MyContentObserver(Context context, Handler handler) {
        super(handler);
        this.context = context;
    }

    @Override
    public void onChange(boolean selfChange) {
        Toast.makeText(context, "数据发生变化", Toast.LENGTH_SHORT).show();
        super.onChange(selfChange);
    }
}
