package com.example.x

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import de.hdodenhof.circleimageview.CircleImageView

class ProfileActivity : AppCompatActivity() {
    private lateinit var logOutBtn:TextView
    private lateinit var backBtn:ImageView
    private lateinit var userName:EditText
    private lateinit var userImage: CircleImageView
    private lateinit var userAge:EditText
    private lateinit var userGender:EditText
    private lateinit var userPhone:EditText
    private lateinit var userDistrict:EditText
    private lateinit var userAadhar:EditText
    private lateinit var userAddress:EditText
    private lateinit var mAuth: FirebaseAuth
    private lateinit var saveBtn:Button
    private lateinit var updateBtn:Button
    private lateinit var db:DatabaseReference
    private lateinit var uid:String



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        mAuth = FirebaseAuth.getInstance()
        backBtn=findViewById(R.id.backBtnProfile)
        logOutBtn=findViewById(R.id.logOutbtnprofile)
        userGender=findViewById(R.id.gender)
        userName=findViewById(R.id.name)
        userPhone=findViewById(R.id.phone)
        userAadhar=findViewById(R.id.aadhar)
        userAge=findViewById(R.id.age)
        userDistrict=findViewById(R.id.district)
        userAddress=findViewById(R.id.address)
        saveBtn=findViewById(R.id.save_profile_btn)
        updateBtn=findViewById(R.id.update_profile_btn)
        uid = intent.getStringExtra("uid").toString()


        val sharedPref=getSharedPreferences("mypref",Context.MODE_PRIVATE)
        val editor=sharedPref.edit()
        loadProfileData(sharedPref)


        logOutBtn.setOnClickListener{
            Toast.makeText(this@ProfileActivity,"Signing Out...", Toast.LENGTH_SHORT).show()
            mAuth.signOut()
            val intent=Intent(this,login_activity::class.java)
            startActivity(intent)
            finish()
        }
        saveBtn.setOnClickListener {
            val fullName=userName.text.toString()
            val age=userAge.text.toString()
            val gender=userGender.text.toString()
            val aadhar=userAadhar.text.toString()
            val phone=userPhone.text.toString()
            val district=userDistrict.text.toString()
            val address=userAddress.text.toString()
            addProfileData(fullName,age,gender,phone,district,aadhar,address)
            Toast.makeText(this,"saving....",Toast.LENGTH_SHORT).show()
            editor.apply {
                putString("fullName",fullName)
                putString("age",age)
                putString("gender",gender)
                putString("aadhar",aadhar)
                putString("phone",phone)
                putString("district",district)
                putString("address",address)
                apply()
            }
            loadProfileData(sharedPref)
        }

        backBtn.setOnClickListener{
            val intent= Intent(this,MainActivity::class.java)
            intent.putExtra("uid",uid)
            finish()
            startActivity(intent)
        }

        updateBtn.setOnClickListener{
//            val intent=Intent(this,UpdateProfile::class.java)
//            intent.putExtra("uid",uid)
//            finish()
//            startActivity(intent)
            val fullName=userName.text.toString()
            val age=userAge.text.toString()
            val gender=userGender.text.toString()
            val aadhar=userAadhar.text.toString()
            val phone=userPhone.text.toString()
            val district=userDistrict.text.toString()
            val address=userAddress.text.toString()
            Toast.makeText(this,"Updating..",Toast.LENGTH_SHORT).show()
            addProfileData(fullName,age,gender,phone,district,aadhar,address)
            editor.apply {
                putString("fullName",fullName)
                putString("age",age)
                putString("gender",gender)
                putString("aadhar",aadhar)
                putString("phone",phone)
                putString("district",district)
                putString("address",address)
                apply()
            }
            loadProfileData(sharedPref)
        }

    }

    private fun loadProfileData(sharedPref: SharedPreferences?) {
        val name= sharedPref?.getString("fullName",null)
        val age= sharedPref?.getString("age",null)
        val gender= sharedPref?.getString("gender",null)
        val phone= sharedPref?.getString("phone",null)
        val district= sharedPref?.getString("district",null)
        val address= sharedPref?.getString("address",null)
        val aadhar= sharedPref?.getString("aadhar",null)

        userName.setText(name)
        userPhone.setText(phone)
        userAge.setText(age)
        userDistrict.setText(district)
        userAddress.setText(address)
        userAadhar.setText(aadhar)
        userGender.setText(gender)

    }


    private fun addProfileData(fullName: String, age: String, gender: String, phone: String, district: String, aadhar: String, address: String) {
        db=FirebaseDatabase.getInstance().reference
        val id=FirebaseAuth.getInstance().currentUser!!.uid
        db.child("Users").child(id).child("Profile").setValue(profileDetails(fullName,age,gender,phone,district,aadhar,address))
    }


}