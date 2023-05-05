package com.example.x

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


interface ApiInterface {

  @GET()
  fun getData(@Url url: String): Call<responseDataClass>
}