package com.ct.mvvmdaggerkotlin.net

import com.ct.mvvmdaggerkotlin.data.Response
import com.ct.mvvmdaggerkotlin.data.User
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterFace {
    @FormUrlEncoded
    @POST("5c5023f6330000e224c586f5")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Observable<Response>

    @GET("5c514430340000fc25129b9d")
    fun getUsers(): Observable<List<User>>
}