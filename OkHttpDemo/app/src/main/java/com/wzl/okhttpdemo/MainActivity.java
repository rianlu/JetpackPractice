package com.wzl.okhttpdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.wzl.okhttpdemo.interceptor.LoginInterceptor;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity:OkHttp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new LoginInterceptor())
                .build();
        Request request = new Request.Builder()
                .url("http://www.publicobject.com/helloworld.txt")
                .header("Charset", "UTF-8")
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG, String.format("error:%s", e.getMessage()));
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String string = response.body().string();
                    // 可进行相关UI更新操作，但必须是在主线程中
                    Log.d(TAG, String.format("response:%s", string));
                }
            }
        });
    }
}
