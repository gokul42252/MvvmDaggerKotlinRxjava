package com.ct.mvvmdaggerkotlin.ui.login

import com.ct.mvvmdaggerkotlin.db.AppDbHelper
import com.ct.mvvmdaggerkotlin.net.ApiInterFace
import dagger.Module
import dagger.Provides

@Module
class LoginActivityModule {
    @Provides
    internal fun provideLoginViewModel(apiInterFace: ApiInterFace,appDbHelper: AppDbHelper): LoginViewModel {
        return LoginViewModel(apiInterFace,appDbHelper)
    }
}