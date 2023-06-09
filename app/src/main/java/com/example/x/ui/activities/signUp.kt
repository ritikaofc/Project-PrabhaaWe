package com.example.x.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.x.R
import com.example.x.databinding.ActivitySignupBinding
import com.example.x.models.signUpUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class signUp : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var db: DatabaseReference
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        mAuth = FirebaseAuth.getInstance()

        binding.btnSignup.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            val name = binding.name.text.toString()
            if(email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty()){
                signUp(email, password, name)
            }
        }
    }

    private fun signUp(email: String, password: String, name: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    addUserToDatabase(email, password, name, mAuth.currentUser?.uid!!)
                } else {
                    Toast.makeText(this, "Some Error Occurred", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun addUserToDatabase(email: String, password: String, name: String, uid: String) {
        db = FirebaseDatabase.getInstance().reference
        Toast.makeText(this, "adding data", Toast.LENGTH_SHORT).show()
        db.child("Users").child(uid).child("signUp")
            .setValue(signUpUser(name, email, password, uid))
        Toast.makeText(this, "data added", Toast.LENGTH_SHORT).show()
        changeofActivity(uid)
    }

    private fun changeofActivity(uid: String) {
        val intent = Intent(this, ProfileActivity::class.java)
        intent.putExtra("uid", uid)
        finish()
        startActivity(intent)
    }


}


