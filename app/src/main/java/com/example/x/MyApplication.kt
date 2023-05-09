package com.example.x

import android.app.Application

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        sharedPrefer.init(this)
    }
}