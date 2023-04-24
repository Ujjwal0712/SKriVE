package com.example.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.skrive.R
import com.example.skrive.databinding.ActivityAdminHomeBinding
import com.example.utils.fbutils.firebaseAuth

class AdminHome : AppCompatActivity() {
    private lateinit var binding: ActivityAdminHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_SKriVE)
        binding= ActivityAdminHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.viewapplications.setOnClickListener {
            startActivity(Intent(this,AdminViewAppliedApplications::class.java))
        }

        binding.adminsignout.setOnClickListener{
            firebaseAuth.signOut()
            startActivity(Intent(this, Createaccount::class.java))
            finish()

        }

    }
}