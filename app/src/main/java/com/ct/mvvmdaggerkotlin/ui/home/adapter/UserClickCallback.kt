package com.ct.mvvmdaggerkotlin.ui.home.adapter

import com.ct.mvvmdaggerkotlin.data.User


interface UserClickCallback {
    fun onClick(user: User)
}