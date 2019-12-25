package com.wzl.okhttpdemo.interceptor;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class LoginInterceptor implements Interceptor {

    private final String TAG = "LoginInterceptor:OkHttp";

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {

        Request request = chain.request();
        Log.d(TAG, String.format("request: \n1.%s\n2.%s\n3.%s",
                request.url(), chain.connection(), request.headers()));

        Response response = chain.proceed(request);
        Log.d(TAG, String.format("response: \n1.%s\n2.%s",
                response.request().url(), response.headers()));
        return response;
    }
}
