package com.ct.mvvmdaggerkotlin.ui.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ct.mvvmdaggerkotlin.data.User
import com.ct.mvvmdaggerkotlin.databinding.ItemUserListBinding
import com.ct.mvvmdaggerkotlin.ui.base.BaseViewHolder

class UserAdapter(private val userClickCallBack: UserClickCallback) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    internal var userList: List<User>? = null
    fun addItems(data: List<User>) {
        userList = data
        notifyDataSetChanged()
    }

    fun clearItems() {
        userList = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding: ItemUserListBinding = ItemUserListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        binding.callback = userClickCallBack
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return if (userList == null) 0 else userList!!.size
    }

    inner class UserViewHolder(private val mBinding: ItemUserListBinding)
        : BaseViewHolder(mBinding.root) {
        override fun onBind(position: Int) {
            val blog = userList!![position]
            mBinding.user = blog
            mBinding.executePendingBindings()
        }
    }
}