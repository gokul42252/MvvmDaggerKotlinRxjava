package com.ct.mvvmdaggerkotlin.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.ct.mvvmdaggerkotlin.data.User
import com.ct.mvvmdaggerkotlin.db.dao.UserDao

@Database(entities = [User::class], exportSchema = false, version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
