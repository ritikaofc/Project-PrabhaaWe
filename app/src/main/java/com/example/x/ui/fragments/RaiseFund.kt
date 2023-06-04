package com.example.x.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.x.Models.RaiseIssueDetails
import com.example.x.databinding.FragmentRaiseFundBinding
import com.example.x.ui.activities.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

public var countIssues:Int = 0

class RaiseFund : Fragment() {


    private var _binding: FragmentRaiseFundBinding?= null
    private val binding get() = _binding!!
    private lateinit var mAuth: FirebaseAuth
    private lateinit var db: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentRaiseFundBinding.inflate(inflater,container,false)
        mAuth= FirebaseAuth.getInstance()


        binding.saveBtnRaiseIssue.setOnClickListener{
            val Name=binding.issueName.text.toString()
            val Description=binding.issueDescription.text.toString()
            val fundRequired=binding.fundRequired.text.toString()
            val tenure=binding.tenure.text.toString()
            val supportingDocument=binding.supportingDocument.text.toString()
            val upi=binding.UPI.text.toString()
            val accountDetails=binding.accountDetails.text.toString()
            if(isBlank(Name) && isBlank(Description) && isBlank(fundRequired) && isBlank(tenure) && isBlank(supportingDocument) && isBlank(upi) && isBlank(accountDetails)){
                countIssues +=1
                addingRaiseIssueData(Name,Description,fundRequired,tenure,supportingDocument,upi,accountDetails,
                    countIssues
                )
            }
            else {
                Toast.makeText(context,"Fill all the fields",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Toast.makeText(context,"saving issue to issue history",Toast.LENGTH_SHORT).show()
            binding.issueName.text.clear()
            binding.UPI.text.clear()
            binding.fundRequired.text.clear()
            binding.accountDetails.text.clear()
            binding.issueDescription.text.clear()
            binding.tenure.text.clear()
            binding.supportingDocument.text.clear()

        }

        binding.backBtnRaiseIssue.setOnClickListener{
            val i = Intent(activity, MainActivity::class.java)
            startActivity(i)
        }


        return binding.root
    }

    private fun addingRaiseIssueData(
        issueName: String,
        issueDescription: String,
        fundRequired: String,
        tenure: String,
        supportingDocument: String,
        upi: String,
        accountDetails: String,
        countIssues:Int
    ) {
        db= FirebaseDatabase.getInstance().reference
        val id=FirebaseAuth.getInstance().currentUser!!.uid
        Toast.makeText(context,"value of count issues is ${countIssues}..",Toast.LENGTH_SHORT).show()
        db.child("Users").child(id).child("Issues").child(countIssues.toString()).setValue(
            RaiseIssueDetails(issueName,issueDescription,fundRequired
        ,tenure,supportingDocument,upi,accountDetails)
        )
    }

    private fun isBlank(string: String) : Boolean{
        if(string.isEmpty()){
            return false
        }
        return true
    }


    companion object {

        @JvmStatic
        fun newInstance() = RaiseFund()

    }

}