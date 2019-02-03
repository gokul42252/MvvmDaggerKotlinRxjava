package com.ct.mvvmdaggerkotlin.ui.base

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<N> : ViewModel() {

    private val isLoading: ObservableBoolean
        get() = ObservableBoolean(false)

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()


    private var mNavigator: N? = null
    fun getNavigator(): N? {
        return mNavigator
    }

    fun setNavigator(navigator: N) {
        this.mNavigator = navigator
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun setIsLoading(isLoading: Boolean) {
        this.isLoading.set(isLoading)
    }


    fun getCompositeDisposable(): CompositeDisposable {
        return compositeDisposable
    }

}