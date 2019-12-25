package com.example.pagingdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private AppDataBase dataBase;
    private LiveData<PagedList<User>> liveUserList;
    private UserDao userDao;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private Button loadBtn, clearBtn;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        dataBase = AppDataBase.getInstance(this);
        userDao = dataBase.getUserDao();
        liveUserList = new LivePagedListBuilder<>(userDao.getAllUsers(), 10).build();
        liveUserList.observe(this, new Observer<PagedList<User>>() {
            @Override
            public void onChanged(PagedList<User> users) {
                adapter.submitList(users);
            }
        });

        loadBtn = findViewById(R.id.load);
        clearBtn = findViewById(R.id.clear);
        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 1000; i++) {
                    User user = new User();
                    user.setName("用户" + (i + 1));
                    new InsertAsyncTask(userDao).execute(user);
                }
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ClearAsyncTask(userDao).execute();
            }
        });
    }

    static class InsertAsyncTask extends AsyncTask<User, Void, Void> {

        UserDao userDao;

        InsertAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }
        @Override
        protected Void doInBackground(User... users) {
            userDao.insertUser(users);
            return null;
        }
    }

    static class ClearAsyncTask extends AsyncTask<Void, Void, Void> {
        UserDao userDao;

        ClearAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteUsers();
            return null;
        }
    }


}
