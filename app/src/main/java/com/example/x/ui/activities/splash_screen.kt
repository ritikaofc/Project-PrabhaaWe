package com.example.x.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.example.x.databinding.ActivitySplashScreenBinding
import com.example.x.utils.sharedPrefer

class splash_screen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        Handler(Looper.getMainLooper()).postDelayed({

            val isLoggedIn = sharedPrefer.getBoolean("isUserLoggedIn")
            if (isLoggedIn) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, login_activity::class.java)
                startActivity(intent)
                finish()
            }
        }, 3000)

    }
}

