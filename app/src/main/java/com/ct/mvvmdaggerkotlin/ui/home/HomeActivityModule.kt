package com.ct.mvvmdaggerkotlin.ui.home

import com.ct.mvvmdaggerkotlin.db.AppDbHelper
import com.ct.mvvmdaggerkotlin.net.ApiInterFace
import dagger.Module
import dagger.Provides

@Module
class HomeActivityModule {
    @Provides
    internal fun provideHomeViewModel(apiInterFace: ApiInterFace, appDbHelper: AppDbHelper): HomeViewModel {
        return HomeViewModel(apiInterFace,appDbHelper)
    }
}