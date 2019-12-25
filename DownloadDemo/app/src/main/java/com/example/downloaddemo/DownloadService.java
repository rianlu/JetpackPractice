package com.example.downloaddemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class DownloadService extends Service {

    private DownloadTask downloadTask;
    private DownloadBinder binder = new DownloadBinder();
    private MyDownloadListener listener = new MyDownloadListener();
    private LocalBroadcastManager localBroadcastManager;

    private final String TAG = "DownloadService";


    public DownloadService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    private class MyDownloadListener implements DownloadListener {

        @Override
        public void onProgress(int progress) {
            Log.d(TAG, "onProgress: " + progress);
            Intent intent = new Intent(MainActivity.BROADCAST_UPDATE_PROGRESS);
            intent.putExtra("currentProgress", progress);
            localBroadcastManager.sendBroadcast(intent);
        }

        @Override
        public void onSuccess() {
            downloadTask = null;
            Toast.makeText(DownloadService.this, "下载成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFail() {
            downloadTask = null;
            Toast.makeText(DownloadService.this, "下载失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPause() {
            downloadTask = null;
            Toast.makeText(DownloadService.this, "下载暂停", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {
            downloadTask = null;
            Toast.makeText(DownloadService.this, "取消下载", Toast.LENGTH_SHORT).show();
        }
    }

    class DownloadBinder extends Binder {

        void startDownload(String url) {
            Log.d(TAG, "startDownload: ");
            if (downloadTask == null) {
                downloadTask = new DownloadTask(listener);
                downloadTask.execute(url);
                Toast.makeText(DownloadService.this, "下载中...", Toast.LENGTH_SHORT).show();
            }
        }

        void pauseDownload() {
            if (downloadTask != null) {
                downloadTask.pauseDownload();
            }
        }

        void cancelDownload() {
            if (downloadTask != null) {
                downloadTask.cancelDownload();
            }
        }
    }
}
