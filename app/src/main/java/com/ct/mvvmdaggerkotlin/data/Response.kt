package com.ct.mvvmdaggerkotlin.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Response {
    @SerializedName("success")
    @Expose
    var isSuccess: Boolean = false
    @SerializedName("data")
    @Expose
    var data: Any? = null
}
