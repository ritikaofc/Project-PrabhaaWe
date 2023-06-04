package com.example.x.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.example.x.R
import com.example.x.sharedPrefer

class splash_screen : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);


        Handler(Looper.getMainLooper()).postDelayed({

            val isLoggedIn= sharedPrefer.getBoolean("isUserLoggedIn")
            if(isLoggedIn){
                val intent= Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                val intent= Intent(this, login_activity::class.java)
                startActivity(intent)
                finish()
            }


        },3000)



    }
}

