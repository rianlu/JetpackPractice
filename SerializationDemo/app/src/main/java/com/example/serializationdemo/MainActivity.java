package com.example.serializationdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Gson gson = new Gson();
        String json = "[{\"name\":\"Android\",\"price\":1000},{\"name\":\"Android\",\"price\":1000}]";
        Type type = new TypeToken<List<Phone>>() {}.getType();
        List<Phone> list = gson.fromJson(json, type);
    }


    public void save(View view) {
        Student student = new Student("Tom", 20, new Score(60, 61));
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(openFileOutput("student.data", MODE_PRIVATE));
            objectOutputStream.writeObject(student);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void load(View view) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(openFileInput("student.data"));
            Student student = (Student) objectInputStream.readObject();
            Log.d(TAG, "load: " + student.toString());
            textView.setText(student.toString());
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void useParcelable(View view) {

        User user = new User("Jack", 20);
        Intent intent = new Intent(this, ParcelableActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("user", user);
        intent.putExtra("data", bundle);
        startActivity(intent);
    }
}
