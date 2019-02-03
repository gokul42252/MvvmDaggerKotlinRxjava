/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.ct.mvvmdaggerkotlin.utils

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.ct.mvvmdaggerkotlin.data.User
import com.ct.mvvmdaggerkotlin.ui.home.adapter.UserAdapter


@BindingAdapter("adapter")
fun addBlogItems(recyclerView: RecyclerView, user: List<User>?) {
    val adapter = recyclerView.adapter as UserAdapter?
    if (adapter != null) {
        adapter.clearItems()
        adapter.addItems(user!!)
    }
}