package com.example.x.api

import com.example.x.Models.responseDataClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


interface ApiInterface {

  @GET()
  fun getData(@Url url: String): Call<responseDataClass>
}