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
import com.example.x.ui.activities.forget_password_activity
import com.example.x.sharedPrefer
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.SignInMethodQueryResult


class login_activity : AppCompatActivity() {

    private lateinit var edtEmail:EditText
    private lateinit var edtPassword:EditText
    private lateinit var btnSignIn: Button
    private lateinit var txtRecover:TextView
    private  lateinit var txtSignup:TextView


    //creating a firebase auth
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //to hide the title bar
        supportActionBar?.hide()
        //for full screen display
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //getting an instance of the same
        mAuth = FirebaseAuth.getInstance()

        edtEmail=findViewById(R.id.email)
        edtPassword=findViewById(R.id.password)
        btnSignIn=findViewById(R.id.btn_signin)
        txtRecover=findViewById(R.id.recover)
        txtSignup=findViewById(R.id.btn_signup)


        txtSignup.setOnClickListener{
            val intent=Intent(this, signUp::class.java)
            startActivity(intent)
        }

        txtRecover.setOnClickListener{
            val intent=Intent(this, forget_password_activity::class.java)
            startActivity(intent)
        }

        btnSignIn.setOnClickListener{
            val email= edtEmail.text.toString()
            val password = edtPassword.text.toString()

            //function to login the user
            signIn(email,password)
        }

    }

    private fun signIn(email: String, password: String) {
        //login for logging user who already has an account
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    sharedPrefer.putBoolean("isUserLoggedIn", true)
                    val intent =Intent(this, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                }
                else {
                    // If sign in fails, display a message to the user.
                    // either user does not exist or password is wrong
                    mAuth.fetchSignInMethodsForEmail(email)
                        .addOnCompleteListener(OnCompleteListener<SignInMethodQueryResult?> { task ->
                            val isNewUser = task.result.signInMethods?.isEmpty()
                            if (isNewUser==true) {
                                Toast.makeText(this@login_activity,"User Does not Exist", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(this@login_activity,"Password Wrong", Toast.LENGTH_SHORT).show()
                            }
                        })

                }
            }
    }


}