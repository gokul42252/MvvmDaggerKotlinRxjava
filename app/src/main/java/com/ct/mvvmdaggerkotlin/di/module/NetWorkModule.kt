package com.ct.mvvmdaggerkotlin.di.module

import com.ct.mvvmdaggerkotlin.BuildConfig
import com.ct.mvvmdaggerkotlin.di.anno.DomainUrl
import com.ct.mvvmdaggerkotlin.net.ApiInterFace
import com.ct.mvvmdaggerkotlin.utils.AppConstants.LIVE_DOMAIN_URL
import com.ct.mvvmdaggerkotlin.utils.AppConstants.LOCAL_DOMAIN_URL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Module which provides all required dependencies about network
 */

@Module
class NetWorkModule {

    /**
     * get ApiInterface.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return ApiInterface.
     */
    @Provides
    @Reusable
    fun getApiInterface(retrofit: Retrofit): ApiInterFace {
        return retrofit.create(ApiInterFace::class.java)
    }

    /**
     * get Retrofit object.
     * @return the Retrofit object
     *
     */
    @Provides
    @Reusable
    fun getRetrofit(okHttpClient: OkHttpClient, @DomainUrl domainUrl: String): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(domainUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }


    @Provides
    @Singleton
    fun getOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    /**
     * Get base url based on build type.
     */
    @Provides
    @DomainUrl
    fun getDomainUrl(): String {
        return when (BuildConfig.BUILD_TYPE) {
            "release" -> LIVE_DOMAIN_URL
            "debug" -> LOCAL_DOMAIN_URL
            else -> LIVE_DOMAIN_URL
        }
    }
}