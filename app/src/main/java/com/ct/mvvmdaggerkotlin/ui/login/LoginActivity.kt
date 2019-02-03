package com.ct.mvvmdaggerkotlin.ui.login

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.ct.mvvmdaggerkotlin.BR
import com.ct.mvvmdaggerkotlin.R
import com.ct.mvvmdaggerkotlin.databinding.ActivityLoginBinding
import com.ct.mvvmdaggerkotlin.net.ApiInterFace
import com.ct.mvvmdaggerkotlin.ui.base.BaseActivity
import com.ct.mvvmdaggerkotlin.ui.home.HomeActivity
import javax.inject.Inject

open class LoginActivity :
    BaseActivity<com.ct.mvvmdaggerkotlin.databinding.ActivityLoginBinding,
            LoginViewModel>(), LoginNavigator {

    override fun navigateToHome() {
        startActivity(newIntent(this))
    }

    @Inject
    lateinit var mLoginViewModel: LoginViewModel

    private var mActivityLoginBinding: ActivityLoginBinding? = null

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun getViewModel(): LoginViewModel {
        return mLoginViewModel
    }

    override fun login(apiInterFace: ApiInterFace) {
        val email = mActivityLoginBinding!!.edtUname.text.toString()
        val password = mActivityLoginBinding!!.edtPass.text.toString()
        if (mLoginViewModel.isValidUser(email, password)) {
            mLoginViewModel.mIsValidUser.value = true
            mLoginViewModel.login(email, password)
        } else {
            mLoginViewModel.mIsValidUser.value = false
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityLoginBinding = viewDataBinding
        mLoginViewModel.setNavigator(this)
        mLoginViewModel.mResponseMsg.observe(this, Observer { msg ->
            if (!TextUtils.isEmpty(msg))
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        })
        mLoginViewModel.mIsValidUser.observe(this, Observer {it->
            if(it==false){
                mActivityLoginBinding!!.edtUname.error = "invalid email"
            }
        })
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }
}

