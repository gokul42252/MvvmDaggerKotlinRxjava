package com.ct.mvvmdaggerkotlin.ui.home

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.widget.Toast
import com.ct.mvvmdaggerkotlin.BR
import com.ct.mvvmdaggerkotlin.R
import com.ct.mvvmdaggerkotlin.data.User
import com.ct.mvvmdaggerkotlin.databinding.ActivityHomeBinding
import com.ct.mvvmdaggerkotlin.ui.base.BaseActivity
import com.ct.mvvmdaggerkotlin.ui.home.adapter.UserAdapter
import com.ct.mvvmdaggerkotlin.ui.home.adapter.UserClickCallback
import javax.inject.Inject

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), HomeNavigator {
    override fun onLoaded() {

    }

    @Inject
    lateinit var mHomeViewModel: HomeViewModel

    private var userAdapter: UserAdapter? = null

    private var mActivityHomeBinding: ActivityHomeBinding? = null

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun getViewModel(): HomeViewModel {
        return mHomeViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityHomeBinding = viewDataBinding
        mHomeViewModel.setNavigator(this)
        userAdapter = UserAdapter(userClickCallBack)
        mActivityHomeBinding!!.userRecyclerView.adapter = userAdapter
        mHomeViewModel.getUsers()
        mHomeViewModel.mUserLiveList.observe(this, Observer { userList ->
            mHomeViewModel.addUserItemsToList(userList!!)
            userAdapter!!.clearItems()
            userAdapter!!.addItems(userList)
        })
    }

    private val userClickCallBack = object : UserClickCallback {
        override fun onClick(user: User) {
            Toast.makeText(applicationContext, user.email, Toast.LENGTH_SHORT).show()
        }
    }
}
