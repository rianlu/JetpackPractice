package com.example.servicedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private Button button1, button2, button3;
    private MyServiceConnection connection;
    private Intent startIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        connection = new MyServiceConnection();
        startIntent = new Intent(this, MyService.class);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button1:
                Log.d(TAG, "onClick: 开始绑定服务");
                startService(startIntent);
                bindService(startIntent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.button2:
                Log.d(TAG, "onClick: 停止服务");
                unbindService(connection);
                stopService(startIntent);
                break;
            case R.id.button3:
                Log.d(TAG, "onClick: 开始解绑");
                Intent bindIntent = new Intent(this, MyService.class);
                unbindService(connection);
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
                break;
        }
    }

    class MyServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

}
