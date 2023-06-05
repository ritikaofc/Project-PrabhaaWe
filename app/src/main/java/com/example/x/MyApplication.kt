package com.example.x

import android.app.Application
import com.example.x.utils.sharedPrefer

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        sharedPrefer.init(this)
    }
}