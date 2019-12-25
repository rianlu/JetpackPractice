package com.example.roomdemo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User(name: String?) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "user_name")
    var name: String? = null

    init {
        this.name = name
    }
    constructor(id: Int, name: String?) : this(name) {
        this.id = id
        this.name = name
    }

    override fun toString(): String {
        return "User(id=$id, name=$name)"
    }
}
