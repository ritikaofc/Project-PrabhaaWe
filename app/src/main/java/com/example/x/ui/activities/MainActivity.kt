package com.example.x.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.x.*
import com.example.x.ui.fragments.Home_Fragment
import com.example.x.ui.fragments.RaiseFund
import com.example.x.ui.fragments.TransactionsFragment
import com.example.x.ui.fragments.fragment_issue_history
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var uid:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val bottom_bar = findViewById<BottomNavigationView>(R.id.bottom_bar)

        uid = intent.getStringExtra("uid").toString()

        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);


        bottom_bar.setOnItemSelectedListener { menuItem ->

            when(menuItem.itemId){
                R.id.nav_home ->{
                    inflateFragment(Home_Fragment.newInstance())
                }
                R.id.nav_issue ->{
                    inflateFragment(RaiseFund.newInstance())
                 }
               R.id.nav_issue_history ->{
                   inflateFragment(fragment_issue_history.newInstance())
               }
                R.id.nav_transaction ->{
                    inflateFragment(TransactionsFragment.newInstance())
                }
            }
            true
        }
        bottom_bar.selectedItemId= R.id.nav_home

        //ApiCall


    }



    private fun inflateFragment(newInstance:Fragment ) {
            val transaction =supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container,newInstance)
            transaction.commit()
        }


}