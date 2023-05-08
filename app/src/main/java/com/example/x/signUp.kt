package com.example.x

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class signUp : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private  lateinit var btnSignUp: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var db:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        supportActionBar?.hide()
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mAuth= FirebaseAuth.getInstance()
        edtName=findViewById(R.id.name)
        edtEmail=findViewById(R.id.email)
        edtPassword=findViewById(R.id.password)
        btnSignUp=findViewById(R.id.btn_signup)


        btnSignUp.setOnClickListener{
            val email= edtEmail.text.toString()
            val password= edtPassword.text.toString()
            val name=edtName.text.toString()

            //function to signup the user
            signUp(email,password,name)
        }

    }

    private fun signUp(email: String, password: String,name:String) {
        //Logic of creating user
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign up success, update UI with the signed-in user's information
                    addUserToDatabase(email,password,name)
                    val intent= Intent(this,MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    // If sign up fails, display a message to the user.
                    Toast.makeText(this,"Some Error Occurred",Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun addUserToDatabase(email: String, password: String, name: String) {
        db=FirebaseDatabase.getInstance().reference
        Toast.makeText(this,"adding data to user",Toast.LENGTH_SHORT).show()
        db.child("Users").child(email).child("signUp").setValue(signUpUser(name, email, password))
        Toast.makeText(this,"added data to user",Toast.LENGTH_SHORT).show()
    }

}