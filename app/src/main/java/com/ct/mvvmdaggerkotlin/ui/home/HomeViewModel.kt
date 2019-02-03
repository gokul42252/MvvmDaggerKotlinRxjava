package com.ct.mvvmdaggerkotlin.ui.home

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.util.Log
import android.view.View
import com.ct.mvvmdaggerkotlin.data.User
import com.ct.mvvmdaggerkotlin.db.AppDbHelper
import com.ct.mvvmdaggerkotlin.net.ApiInterFace
import com.ct.mvvmdaggerkotlin.ui.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel(
    private val apiInterFace: ApiInterFace,
    private val appDbHelper: AppDbHelper
) : BaseViewModel<HomeNavigator>() {

    val mUsersList: ObservableList<User> = ObservableArrayList<User>()
    var mUserLiveList: MutableLiveData<List<User>> = MutableLiveData()
    private val mLoadingVisibility: MutableLiveData<Int> = MutableLiveData()
    /**
     * Get users list.
     */
    fun getUsers() {
        getCompositeDisposable().add(
            getUsersList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { mLoadingVisibility.value = View.VISIBLE }
                .doOnTerminate { mLoadingVisibility.value = View.GONE }
                .subscribe({ response ->
                    if (response == null) return@subscribe
                    else {
                        mUserLiveList.value = response
                    }
                }, { t ->
                    Log.d("", t.toString())

                })
        )
    }

    private fun getUsersList(): Observable<List<User>> {
        val observableFromApi = getUsersFromApi()
        val observableFromDb = getUsersFromDb()
        return Observable.concatArrayEager(observableFromApi)
    }

    private fun getUsersFromApi(): Observable<List<User>> {
        return apiInterFace.getUsers().doOnNext {
            for (item in it) {
                appDbHelper.insertUsers(item)
            }
        }
    }


    private fun getUsersFromDb(): Observable<List<User>> {
        return appDbHelper.getAllUsers()
    }

    fun addUserItemsToList(users: List<User>) {
        mUsersList.clear()
        mUsersList.addAll(users)
    }

}