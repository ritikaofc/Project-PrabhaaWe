package com.example.x

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

object sharedPrefer {
    private  const val NAME="sharedPrefer"
    private lateinit var preferences:SharedPreferences
    fun init(context: Context)
    {
        preferences=context.getSharedPreferences(NAME,MODE_PRIVATE)
    }

    fun putBoolean(key:String,value:Boolean){
        preferences.edit().putBoolean(key,value).apply()
    }
    fun getBoolean(key: String):Boolean
    {
       return preferences.getBoolean(key,false)
    }
}