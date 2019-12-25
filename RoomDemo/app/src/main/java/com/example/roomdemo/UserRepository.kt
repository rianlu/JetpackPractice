package com.example.roomdemo

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class UserRepository(context: Context) {

    private var userDao: UserDao? = null
    private var userLiveData: LiveData<List<User>>? = null;

    init {
        val db: AppDataBase = AppDataBase.getInstance(context.applicationContext)
        userDao = db.userDao();
        userLiveData = userDao?.getAll()
    }

    fun getUserLiveData(): LiveData<List<User>>? {
        return userLiveData
    }

    fun insertUsers(vararg user: User) {
        InsertAsyncTask(userDao).execute(*user)
    }

    fun delUser(user: User) {
        DelAsyncTask(userDao).execute(user)
    }

    fun updateUser(user: User) {
        UpdateAsyncTask(userDao).execute(user)
    }

    class InsertAsyncTask(userDao: UserDao?) : AsyncTask<User, Void, Void>() {

        private var userDao: UserDao? = null

        init {
            this.userDao = userDao
        }

        override fun doInBackground(vararg params: User): Void? {
            userDao?.insertUsers(*params)
            return null
        }
    }

    class DelAsyncTask(userDao: UserDao?) : AsyncTask<User, Void, Void>() {

        private var userDao: UserDao? = null

        init {
            this.userDao = userDao
        }

        override fun doInBackground(vararg params: User): Void? {
            userDao?.deleteUser(params[0])
            return null
        }
    }

    class UpdateAsyncTask(userDao: UserDao?) : AsyncTask<User, Void, Void>() {

        private var userDao: UserDao? = null

        init {
            this.userDao = userDao
        }

        override fun doInBackground(vararg params: User): Void? {
            userDao?.updateUser(params[0])
            return null
        }
    }
}
