package com.example.x

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("/1")
    fun getData(): Call<responseDataClass>
}