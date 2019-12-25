package com.example.roomdemo;

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var addBtn: Button? = null
    var delBtn: Button? = null
    var updateBtn: Button? = null
    var viewModel: UserViewModel? = null
    var recyclerView: RecyclerView? = null
    var adapter1: RecyclerViewAdapter? = null
    var adapter2: RecyclerViewAdapter? = null
    var switch: Switch? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addBtn = findViewById(R.id.add_user)
        delBtn = findViewById(R.id.del)
        updateBtn = findViewById(R.id.update)
        recyclerView = findViewById(R.id.recyclerView)
        switch = findViewById(R.id.switch1)

        adapter1 = RecyclerViewAdapter(false)
        adapter2 = RecyclerViewAdapter(true)

        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.adapter = adapter1

        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        viewModel!!.getUserLiveData()!!.observe(this, Observer { userList ->

            adapter1?.submitList(userList)
            adapter2?.submitList(userList)
        })

        addBtn?.setOnClickListener(this)
        delBtn?.setOnClickListener(this)
        updateBtn?.setOnClickListener(this)
        switch?.setOnCheckedChangeListener {
            _, isChecked ->
            if (!isChecked) {
                recyclerView?.adapter = adapter1
            } else {
                recyclerView?.adapter = adapter2
            }
        }


    }

    override fun onClick(v: View?) {
        print("dianji")
        when (v?.id) {
            R.id.add_user -> {
                val user1 = User("hhh")
                val user2 = User("eee")
                //Thread(Runnable { db!!.userDao().insertUser(user1) }).start()
                viewModel?.insertUsers(user1, user2)
            }
            R.id.del -> {
                val user = User(1, "hhh")
                viewModel?.delUser(user)
            }
            R.id.update -> {
                val user = User(1, "eee")
                viewModel?.updateUser(user)
            }

        }

    }
}

