package com.example.roomdemo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private var userRepository: UserRepository? = null

    init {
        userRepository = UserRepository(application)
    }

    fun getUserLiveData(): LiveData<List<User>>? {
        return userRepository?.getUserLiveData()
    }

    fun insertUsers(vararg user: User) {
        userRepository?.insertUsers(*user)
    }

    fun delUser(user: User) {
        userRepository?.delUser(user)
    }

    fun updateUser(user: User) {
        userRepository?.updateUser(user)
    }
}
