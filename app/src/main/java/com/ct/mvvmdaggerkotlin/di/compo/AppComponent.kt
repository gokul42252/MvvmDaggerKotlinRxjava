package com.ct.mvvmdaggerkotlin.di.compo

import android.app.Application
import com.ct.mvvmdaggerkotlin.App
import com.ct.mvvmdaggerkotlin.di.builder.ActivityBuilder
import com.ct.mvvmdaggerkotlin.di.module.AppModule
import com.ct.mvvmdaggerkotlin.di.module.NetWorkModule
import com.ct.mvvmdaggerkotlin.net.ApiInterFace
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBuilder::class,NetWorkModule::class])
interface AppComponent {

    fun inject(app: App)

    fun getApiInterface(): ApiInterFace

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}