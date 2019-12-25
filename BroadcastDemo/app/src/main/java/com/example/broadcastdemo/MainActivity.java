package com.example.broadcastdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    //private MyReceiver receiver;
    //private IntentFilter intentFilter;
    //private LocalBroadcastManager localBroadcastManager;
    private NormalReceiver normalReceiver;
    private StickyReceiver stickyReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //intentFilter = new IntentFilter();
        //intentFilter.addAction("com.example.broadcastdemo.MY_BROADCAST");
        //receiver = new MyReceiver();
        //localBroadcastManager = LocalBroadcastManager.getInstance(this);

        normalReceiver = new NormalReceiver();
        stickyReceiver = new StickyReceiver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unregisterReceiver(receiver);
        unregisterReceiver(normalReceiver);
        unregisterReceiver(stickyReceiver);
    }

    public void sendNormal(View view) {
        Intent intent = new Intent("com.example.broadcastdemo.NORMAL_BROADCAST");
        sendBroadcast(intent);
    }

    public void registerNormal(View view) {
        IntentFilter filter = new IntentFilter("com.example.broadcastdemo.NORMAL_BROADCAST");
        registerReceiver(normalReceiver, filter);
    }

    public void sendSticky(View view) {
        Intent intent = new Intent("com.example.broadcastdemo.STICKY_BROADCAST");
        sendStickyBroadcast(intent);
    }

    public void registerSticky(View view) {
        IntentFilter filter = new IntentFilter("com.example.broadcastdemo.STICKY_BROADCAST");
        registerReceiver(stickyReceiver, filter);

    }

}

class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "收到本地广播", Toast.LENGTH_SHORT).show();
    }
}

class NormalReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "收到普通广播", Toast.LENGTH_SHORT).show();
    }
}

class StickyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "收到粘性广播", Toast.LENGTH_SHORT).show();

    }
}

