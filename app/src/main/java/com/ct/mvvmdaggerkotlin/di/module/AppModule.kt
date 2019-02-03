package com.ct.mvvmdaggerkotlin.di.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.ct.mvvmdaggerkotlin.db.AppDatabase
import com.ct.mvvmdaggerkotlin.db.dao.UserDao
import com.ct.mvvmdaggerkotlin.di.anno.DbName
import com.ct.mvvmdaggerkotlin.utils.AppConstants.DB_NAME
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@DbName dbName: String, context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, dbName)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: AppDatabase): UserDao = database.userDao()

    @Provides
    @DbName
    fun provideDatabaseName(): String {
        return DB_NAME
    }

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }


    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }


}