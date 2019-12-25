package com.example.downloaddemo;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class DownloadTask extends AsyncTask<String, Integer, Integer> {

    private final String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
    private static final int SUCCESS = 0;
    private static final int FAIL = 1;
    private static final int PAUSE = 2;
    private static final int CANCEL = 3;

    private boolean isPause = false;
    private boolean isCancel = false;

    private int lastProgress = 0;

    private DownloadListener listener;

    private String TAG = "DownloadTask";

    DownloadTask(DownloadListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Integer doInBackground(String... strings) {
        String url = strings[0];
        try {
            return startDownload(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FAIL;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {

        int progress = values[0];
        if (progress > lastProgress) {
            listener.onProgress(progress);
            lastProgress = progress;
        }
    }

    @Override
    protected void onPostExecute(Integer integer) {

        switch (integer) {
            case SUCCESS:
                listener.onSuccess();
                break;
            case FAIL:
                listener.onFail();
                break;
            case PAUSE:
                listener.onPause();
                break;
            case CANCEL:
                listener.onCancel();
                break;
        }
    }

    void pauseDownload() {
        isPause = true;
    }

    void cancelDownload() {
        isCancel = true;
    }

    private Integer startDownload(String url) throws IOException {

        long downloadLength = 0;  //记录已下载的文件长度
        File file = null;
        String fileName = url.substring(url.lastIndexOf("/"));
        file = new File(directory + fileName);
        if (file.exists()) {
            downloadLength = file.length();
        }

        long contentLength = getContentLength(url);
        if (contentLength == 0) {
            return FAIL;
        }

        Log.d(TAG, "startDownload: " + "downloadLength:" + downloadLength + " contentLength:" +contentLength);
        if (contentLength == downloadLength) {
            return SUCCESS;
        }

        InputStream in = null;
        RandomAccessFile downloadFile = null;
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .addHeader("RANGE", "bytes=" + downloadLength + "-")
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                if (body != null) {
                    in = body.byteStream();
                    downloadFile = new RandomAccessFile(file, "rw");
                    downloadFile.seek(downloadLength);  //跳过已经下载的内容
                    byte[] bytes = new byte[1024];
                    int len = 0;
                    int total = 0;
                    while ((len = in.read(bytes)) != -1) {
                        if (isPause) {
                            return PAUSE;
                        }
                        if (isCancel) {
                            return CANCEL;
                        }
                        total += len;
                        downloadFile.write(bytes, 0, len);
                        // 计算已下载的百分比
                        int progress = (int) (((total + downloadLength) * 100) / contentLength);
                        publishProgress(progress);
                    }
                    body.close();
                    return SUCCESS;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
            if (downloadFile != null) {
                downloadFile.close();
            }
            if (isCancel && downloadLength < contentLength) {
                file.delete();
            }
        }
        return FAIL;
    }


    private long getContentLength(String url) throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            ResponseBody body = response.body();
            if (body != null) {
                long contentLength = body.contentLength();
                body.close();
                return contentLength;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }
}