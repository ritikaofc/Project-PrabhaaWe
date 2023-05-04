package com.example.x

import retrofit2.Call
import retrofit2.http.GET


interface ApiInterface {

  @GET("5")
  fun getData(): Call<responseDataClass>
}