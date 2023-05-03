package com.example.x

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import de.hdodenhof.circleimageview.CircleImageView
import kotlin.math.log

class ProfileActivity : AppCompatActivity() {
    private lateinit var logOutBtn:TextView
    private lateinit var userName:EditText
    private lateinit var userImage: CircleImageView
    private lateinit var userAge:EditText
    private lateinit var userGender:EditText
    private lateinit var userPhone:EditText
    private lateinit var userEmail:EditText
    private lateinit var useraadhar:EditText
    private lateinit var userAddress:EditText
    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        mAuth = FirebaseAuth.getInstance()
        logOutBtn=findViewById(R.id.logOutbtnprofile)
        userGender=findViewById(R.id.gender)
        userName=findViewById(R.id.name)
        userPhone=findViewById(R.id.phone)
        useraadhar=findViewById(R.id.aadhar)
        userAge=findViewById(R.id.age)
        userEmail=findViewById(R.id.email)
        userAddress=findViewById(R.id.address)

        logOutBtn.setOnClickListener{
            Toast.makeText(this@ProfileActivity,"Signing Out....", Toast.LENGTH_SHORT).show()
            mAuth.signOut()
            val intent=Intent(this,login_activity::class.java)
            startActivity(intent)
            finish()
        }



    }
}