package com.ct.mvvmdaggerkotlin.ui.login

import android.arch.lifecycle.MutableLiveData
import android.text.TextUtils
import android.util.Patterns
import com.ct.mvvmdaggerkotlin.data.User
import com.ct.mvvmdaggerkotlin.db.AppDbHelper
import com.ct.mvvmdaggerkotlin.net.ApiInterFace
import com.ct.mvvmdaggerkotlin.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginViewModel(
    private val apiInterFace: ApiInterFace,
    private val appDbHelper: AppDbHelper
) : BaseViewModel<LoginNavigator>() {

    var user: User = User(1, "", "")
    val mResponseMsg: MutableLiveData<String> = MutableLiveData()
    var mIsValidUser: MutableLiveData<Boolean> = MutableLiveData()
    val mLoadingVisibility: MutableLiveData<Int> = MutableLiveData()
    private val mIsLogin: MutableLiveData<Boolean> = MutableLiveData()
    /**
     * Function used to validate user email and password.
     * @return true if valid user else false.
     */
    fun isValidUser(email:String,pass:String): Boolean {
        return !TextUtils.isEmpty(email)
                && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    fun login(userName: String, pass: String) {
        user.email = userName
        user.password = pass
        getCompositeDisposable().add(
            apiInterFace.login(userName, pass)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { }
                .doOnTerminate { }
                .subscribe({ response ->
                    if (response == null) return@subscribe
                    else {
                        appDbHelper.insertUsers(user)
                        mIsLogin.value = response.isSuccess
                        mResponseMsg.value = mSuccessMsg
                        getNavigator()!!.navigateToHome()
                    }
                }, { throwable ->
                    mResponseMsg.value = throwable.toString()
                    mIsLogin.value = false
                })
        )
    }

    fun isEmailAndPasswordValid() {
        getNavigator()!!.login(apiInterFace)
    }

    companion object {
        private const val mSuccessMsg: String = "Login successful"
        private const val mErrorMsg: String = "Email or Password not valid"
    }
}