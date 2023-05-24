package com.example.x.Repository

import androidx.lifecycle.MutableLiveData
import com.example.x.Models.RaiseIssueDataClass
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.Objects

class RaiseIssueRepository {
    private val id=FirebaseAuth.getInstance().currentUser!!.uid
    private val db:DatabaseReference = FirebaseDatabase.getInstance().reference.child("Users").child(id).child("Issues")

//    whole application there is only one instance of RaiseIssueRepository
    @Volatile private var INSTANCE: RaiseIssueRepository?=null


//    this will make sure that if the instance is null then only it will go inside this function
//    and return a new instance of the same  already there then same instance will be returned
    fun getInstance():RaiseIssueRepository{
        return INSTANCE ?: synchronized(this){
             val instance =RaiseIssueRepository()
             INSTANCE=instance
            instance
        }
    }

    fun loadRaiseIssues(raiseIssueList:MutableLiveData<List<RaiseIssueDataClass>>){
        db.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                try{
                    val _raiseIssueList:List<RaiseIssueDataClass> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(RaiseIssueDataClass::class.java)!!
                    }
                    raiseIssueList.postValue(_raiseIssueList)

                }catch(e:Exception){

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }


}