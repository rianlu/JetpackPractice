package com.example.volleydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.LruCache;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VolleyActivity extends AppCompatActivity {


    private NetworkImageView networkImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        networkImageView = findViewById(R.id.imageView3);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://cdn.pixabay.com/photo/2019/11/26/17/48/girl-4655079__340.jpg";
        ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            private LruCache<String, Bitmap> lruCache = new LruCache<>(50); // 设置缓存大小
            @Override
            public Bitmap getBitmap(String url) {
                return lruCache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                lruCache.put(url, bitmap);
            }
        });
        networkImageView.setDefaultImageResId(R.drawable.ic_launcher_background);
        networkImageView.setErrorImageResId(R.drawable.ic_launcher_background);
        networkImageView.setImageUrl(url, imageLoader);
    }
}
