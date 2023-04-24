package com.example.views

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.skrive.R
import com.example.skrive.databinding.ActivityHomeBinding
import com.example.utils.fbutils.firebaseAuth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private var db = Firebase.firestore



    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setTheme(R.style.Theme_SKriVE)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        val ref= db.collection("user").document(userId)

        ref.get().addOnSuccessListener {
            if (it != null) {
                val name = it.data?.get("Name")?.toString()
                binding.greet.text = ("Hi, $name")

            }
        }
        binding.viewstatus.setOnClickListener{
            startActivity(Intent(this, ViewStatus::class.java))
        }

        binding.applyhostelleave.setOnClickListener {
            startActivity(Intent(this, HostelLeave::class.java))
        }

        binding.calendar.setOnClickListener {
            startActivity(Intent(this, CalendarActivity::class.java))
        }


        binding.profile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        binding.signout.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(this, Createaccount::class.java))
            finish()
        }
    }
}

