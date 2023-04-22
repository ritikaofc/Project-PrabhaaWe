package com.example.x

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottom_bar = findViewById<BottomNavigationView>(R.id.bottom_bar)

        bottom_bar.setOnItemSelectedListener { menuItem ->

            when(menuItem.itemId){
                R.id.nav_issue->{
                    inflateFragment(RaiseFund.newInstance())
                 }
                R.id.nav_profile->{
                    inflateFragment(profileFragment.newInstance())
                }
                R.id.nav_transaction->{
                    inflateFragment(TransactionsFragment.newInstance())
                }
            }
            true
        }
        bottom_bar.selectedItemId= R.id.nav_issue

    }

        private fun inflateFragment(newInstance:Fragment ) {
            val transaction =supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container,newInstance)
            transaction.commit()
        }
}