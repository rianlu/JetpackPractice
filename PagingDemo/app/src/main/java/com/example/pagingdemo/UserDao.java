package com.example.pagingdemo;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Insert
    void insertUser(User... users);

    @Query("DELETE FROM user")
    void deleteUsers();

    @Query("SELECT * FROM user")
    DataSource.Factory<Integer, User> getAllUsers();
}
