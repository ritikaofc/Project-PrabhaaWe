package com.example.x

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.x.databinding.ActivityMainBinding
import com.example.x.databinding.ActivityUpdateProfileBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.activity_signup.name
import kotlinx.android.synthetic.main.activity_update_profile.*
import okhttp3.internal.threadName

private lateinit var binding: ActivityUpdateProfileBinding
private lateinit var database:DatabaseReference
private lateinit var updateBtn:Button
private lateinit var uid:String
class UpdateProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUpdateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateBtn=findViewById(R.id.update_profile_btn)
        uid = intent.getStringExtra("uid").toString()

        binding.updateProfileBtn.setOnClickListener{
            val fullName= binding.name.text.toString()
            val age= binding.age.text.toString()
            val gender= binding.gender.toString()
            val aadhar= binding.aadhar.text.toString()
            val phone= binding.phone.text.toString()
            val district= binding.district.text.toString()
            val address= binding.address.text.toString()
            updateData(fullName,age,gender,aadhar,phone,district,address)

        }
    }

    private fun updateData(fullName: String, age: String, gender: String, aadhar: String, phone: String, district: String, address: String) {
        database=FirebaseDatabase.getInstance().reference
        val user= mapOf<String,String>(
            "fullName" to fullName,
            "age" to age,
            "gender" to gender,
            "aadhar" to aadhar,
            "phone" to phone,
            "district" to district,
            "address" to address

        )

        database.child("Users").child(uid).child("Profile").updateChildren(user).addOnSuccessListener {
            binding.name.text.clear()
            binding.age.text.clear()
            binding.gender.text.clear()
            binding.aadhar.text.clear()
            binding.phone.text.clear()
            binding.district.text.clear()
            binding.address.text.clear()
            Toast.makeText(this,"Successfully updated",Toast.LENGTH_SHORT).show()

        }.addOnFailureListener{
            Toast.makeText(this,"Failed to update",Toast.LENGTH_SHORT).show()
        }
    }
}