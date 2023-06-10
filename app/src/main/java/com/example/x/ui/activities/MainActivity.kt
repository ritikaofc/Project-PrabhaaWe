package com.example.x.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.x.*
import com.example.x.databinding.ActivityMainBinding
import com.example.x.ui.fragments.Home_Fragment
import com.example.x.ui.fragments.RaiseFund
import com.example.x.ui.fragments.TransactionsFragment
import com.example.x.ui.fragments.fragment_issue_history

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var uid: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        uid = intent.getStringExtra("uid").toString()

        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        binding.bottomBar.setOnItemSelectedListener { menuItem ->

            when (menuItem.itemId) {
                R.id.nav_home -> {
                    inflateFragment(Home_Fragment.newInstance())
                }
                R.id.nav_issue -> {
                    inflateFragment(RaiseFund.newInstance())
                }
                R.id.nav_issue_history -> {
                    inflateFragment(fragment_issue_history.newInstance())
                }
                R.id.nav_transaction -> {
                    inflateFragment(TransactionsFragment.newInstance())
                }
            }
            true
        }
        binding.bottomBar.selectedItemId = R.id.nav_home

    }

    private fun inflateFragment(newInstance: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, newInstance)
        transaction.commit()
    }
}