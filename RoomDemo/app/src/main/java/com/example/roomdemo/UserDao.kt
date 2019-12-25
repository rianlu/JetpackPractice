package com.example.roomdemo

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Query("select * from user")
    fun getAll() : LiveData<List<User>>

    @Delete
    fun deleteUser(user: User)

    @Insert
    fun insertUser(user: User)

    @Insert
    fun insertUsers(vararg user: User)

    @Update
    fun updateUser(user: User)

}
