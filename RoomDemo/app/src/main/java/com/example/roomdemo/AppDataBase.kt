package com.example.roomdemo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * 单例模式
 */
@Database(entities = [User::class], version = 3)
abstract class AppDataBase : RoomDatabase() {

    companion object {
        private var dataBase: AppDataBase? = null;

        @Synchronized
        fun getInstance(context: Context): AppDataBase {
            if (dataBase == null) {
                dataBase = Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, "app_roomdemo")
                        .addMigrations(migration2_3)
                        .build()
            }
            return dataBase as AppDataBase
        }

        val migration1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE user ADD COLUMN age INTEGER")
            }
        }

        val migration2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE user_temp (id INTEGER PRIMARY KEY NOT NULL, user_name TEXT)")
                database.execSQL("INSERT INTO user_temp (id, user_name) SELECT id, user_name FROM user"
                )
                database.execSQL("DROP TABLE user")
                database.execSQL("ALTER TABLE user_temp RENAME TO user")
            }
        }
    }

    abstract fun userDao() : UserDao


}
