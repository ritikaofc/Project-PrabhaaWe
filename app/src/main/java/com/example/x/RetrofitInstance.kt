package com.example.x

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy{
        Retrofit.Builder().baseUrl("https://3e38129a-da8d-4a9b-ad2e-f0e972e9dd38.mock.pstmn.io/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    val apiInterfce by lazy{
        retrofit.create(ApiInterface::class.java)
    }

}