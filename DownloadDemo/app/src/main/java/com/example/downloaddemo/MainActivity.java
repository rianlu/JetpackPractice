package com.example.downloaddemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;
    private Button startBtn, pauseBtn, cancelBtn;
    private TextView progressText;
    private ProgressBar progressBar;
    private DownloadService.DownloadBinder binder;
    private String downloadUrl = "";
    public static final String BROADCAST_UPDATE_PROGRESS = "com.example.downloaddemo.UPDATE_PROGRESS";
    private LocalBroadcastManager localBroadcastManager;
    private MyBroadcastReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = findViewById(R.id.startBtn);
        pauseBtn = findViewById(R.id.pauseBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
        editText = findViewById(R.id.editText);
        progressText = findViewById(R.id.currentProgress);
        progressBar = findViewById(R.id.progressBar);

        startBtn.setOnClickListener(this);
        pauseBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);

        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        receiver = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BROADCAST_UPDATE_PROGRESS);
        localBroadcastManager.registerReceiver(receiver, filter);

        progressBar.setMax(100);
        initService();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startBtn:
                downloadUrl = editText.getText().toString();
                binder.startDownload(downloadUrl);
                break;
            case R.id.pauseBtn:
                binder.pauseDownload();
                break;
            case R.id.cancelBtn:
                binder.cancelDownload();
                break;
        }
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (DownloadService.DownloadBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private void initService() {
        Intent downloadIntent = new Intent(this, DownloadService.class);
        startService(downloadIntent);
        bindService(downloadIntent, connection, BIND_AUTO_CREATE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "权限拒绝，程序无法正常运行", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(receiver);
        unbindService(connection);
    }

    class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int currentProgress = intent.getIntExtra("currentProgress", 0);
            progressBar.setProgress(currentProgress);
            progressText.setText(currentProgress + "%");
        }
    }

}
