package com.example.x.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.x.R
import com.example.x.databinding.ActivityLoginBinding
import com.example.x.utils.sharedPrefer
import com.example.x.utils.Network
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.SignInMethodQueryResult


class login_activity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        mAuth = FirebaseAuth.getInstance()


        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, signUp::class.java)
            startActivity(intent)
        }

        binding.recover.setOnClickListener {
            val intent = Intent(this, forget_password_activity::class.java)
            startActivity(intent)
        }

        binding.btnSignin.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            if (isBlank(email) && isBlank(password)) {
                signIn(email, password)
            } else {
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }

    }

    private fun signIn(email: String, password: String) {

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    sharedPrefer.putBoolean("isUserLoggedIn", true)
                    val intent = Intent(this, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    mAuth.fetchSignInMethodsForEmail(email)
                        .addOnCompleteListener { task ->
                            if (Network.isConnected(this)) {
                                val isNewUser = task.result.signInMethods?.isEmpty()
                                if (isNewUser == true) {
                                    Toast.makeText(
                                        this@login_activity,
                                        "User Does not Exist",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Toast.makeText(
                                        this@login_activity,
                                        "Password Wrong",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            } else {
                                Toast.makeText(
                                    this,
                                    "Make sure you are connected to internet",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
            }
    }

    private fun isBlank(string: String): Boolean {
        if (string.isEmpty()) {
            return false
        }
        return true
    }

}