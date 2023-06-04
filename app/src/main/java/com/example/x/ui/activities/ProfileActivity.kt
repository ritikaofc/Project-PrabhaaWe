package com.example.x.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.x.R
import com.example.x.Models.profileDetails
import com.example.x.sharedPrefer
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
    private  var IMAGE_CODE:Int = 0
    private lateinit var imageUri:Uri


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
        IMAGE_CODE=1
        userImage=findViewById(R.id.userprofileImage)

        retrieveDataProfileFromFragment()


        logOutBtn.setOnClickListener{
            Toast.makeText(this@ProfileActivity,"Signing Out...", Toast.LENGTH_SHORT).show()
            sharedPrefer.putBoolean("isUserLoggedIn", false)
            mAuth.signOut()
            val intent=Intent(this, login_activity::class.java)
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
            retrieveDataProfileFromFragment()
        }

        backBtn.setOnClickListener{
            val intent= Intent(this, MainActivity::class.java)
            intent.putExtra("uid",uid)
            finish()
            startActivity(intent)
        }

        userImage.setOnClickListener{

            openImage()
        }

        updateBtn.setOnClickListener{
            val fullName=userName.text.toString()
            val age=userAge.text.toString()
            val gender=userGender.text.toString()
            val aadhar=userAadhar.text.toString()
            val phone=userPhone.text.toString()
            val district=userDistrict.text.toString()
            val address=userAddress.text.toString()
            Toast.makeText(this,"Updating..",Toast.LENGTH_SHORT).show()
            addProfileData(fullName,age,gender,phone,district,aadhar,address)
            retrieveDataProfileFromFragment()
        }

    }

    private fun retrieveDataProfileFromFragment() {
        val id = FirebaseAuth.getInstance().currentUser!!.uid
        db= FirebaseDatabase.getInstance().reference.child("Users").child(id)
        db.child("Profile").get().addOnSuccessListener {
            if (it.exists()) {
                val aadhar=it.child("aadharNumber").value
                val address=it.child("address").value
                val name=it.child("fullName").value
                val age=it.child("age").value
                val district=it.child("district").value
                val gender=it.child("gender").value
                val phone=it.child("phoneNumber").value
                Toast.makeText(this,"Profile info successfully retrieved..",Toast.LENGTH_SHORT).show()
                userName.setText(name.toString())
                userGender.setText(gender.toString())
                userAddress.setText(address.toString())
                userAadhar.setText(aadhar.toString())
                userDistrict.setText(district.toString())
                userAge.setText(age.toString())
                userPhone.setText(phone.toString())

            }else{
                Toast.makeText(this,"Profile info does not exist..",Toast.LENGTH_SHORT).show()
            }

        }.addOnFailureListener{
            Toast.makeText(this,"Unable to fetch data from firebase..",Toast.LENGTH_SHORT).show()
        }
    }

    private fun openImage() {
        intent= Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent,IMAGE_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==IMAGE_CODE && resultCode==RESULT_OK && data!=null && data.data !=null )
        {
            imageUri= data.data!!
            userImage.setImageURI(imageUri)
        }
    }




    private fun addProfileData(fullName: String, age: String, gender: String, phone: String, district: String, aadhar: String, address: String) {
        db=FirebaseDatabase.getInstance().reference
        val id=FirebaseAuth.getInstance().currentUser!!.uid
        db.child("Users").child(id).child("Profile").setValue(profileDetails(fullName,age,gender,phone,district,aadhar,address))
    }

}