package com.example.x.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.x.R
import com.example.x.databinding.ActivityForgetPasswordBinding
import com.example.x.utils.Network
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class forget_password_activity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    private lateinit var binding: ActivityForgetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()

        binding.btnReset.setOnClickListener{
            if(Network.isConnected(this)){
                if(binding.email.text.toString().isNotEmpty()){
                    setNewPassword(binding.email)
                }
            } else {
                Toast.makeText(this,"Make sure you are connected to the internet",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }
    }

    private fun setNewPassword(email :EditText) {
        val userEmail= email.text.toString()
        Firebase.auth.sendPasswordResetEmail(userEmail)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this,"Email sent for setting new Password",Toast.LENGTH_SHORT).show()
                }
            }
    }
}