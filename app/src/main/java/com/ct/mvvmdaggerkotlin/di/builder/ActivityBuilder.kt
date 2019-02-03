package com.ct.mvvmdaggerkotlin.di.builder

import com.ct.mvvmdaggerkotlin.ui.home.HomeActivity
import com.ct.mvvmdaggerkotlin.ui.home.HomeActivityModule
import com.ct.mvvmdaggerkotlin.ui.login.LoginActivity
import com.ct.mvvmdaggerkotlin.ui.login.LoginActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [LoginActivityModule::class])
    internal abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    internal abstract fun bindHomeActivity(): HomeActivity

}
