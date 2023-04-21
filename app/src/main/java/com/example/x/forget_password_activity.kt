package com.example.x

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_forget_password.*

class forget_password_activity : AppCompatActivity() {

    private lateinit var email:EditText
    private lateinit var btnReset :Button
    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        mAuth = FirebaseAuth.getInstance()
        email = findViewById(R.id.email)
        btnReset = findViewById(R.id.btn_reset)


        btnReset.setOnClickListener{
            setNewPassword(email)
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