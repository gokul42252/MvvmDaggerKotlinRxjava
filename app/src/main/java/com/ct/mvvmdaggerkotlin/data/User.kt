package com.ct.mvvmdaggerkotlin.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.text.TextUtils
import android.util.Patterns

@Entity(tableName = "users")
data class User(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "username") var email: String,
    var password: String
) {


}