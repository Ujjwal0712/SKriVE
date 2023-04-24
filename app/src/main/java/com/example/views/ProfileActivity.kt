package com.example.views

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.skrive.R
import com.example.skrive.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private var db =Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_SKriVE)
        binding= ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        val ref= db.collection("user").document(userId)

        ref.get().addOnSuccessListener {
            if(it != null){
                val name= it.data?.get("Name")?.toString()
                val rollno= it.data?.get("Roll No")?.toString()
                val branch= it.data?.get("Branch")?.toString()
                val semester= it.data?.get("Semester")?.toString()
                val email= it.data?.get("email")?.toString()
                val address= it.data?.get("address")?.toString()
                val contact= it.data?.get("contact")?.toString()
                val econtact= it.data?.get("econtact")?.toString()

                binding.nameview.text=name
                binding.rollnoview.text=rollno
                binding.branchview.text=branch
                binding.semesterview.text=semester
                binding.contactview.text=contact
                binding.addressview.text=address
                binding.emailview.text=email
                binding.econtactview.text=econtact
        }
        }
            .addOnFailureListener{
                Toast.makeText(this, "Failed!",Toast.LENGTH_SHORT).show()
            }

        binding.calendarview.setOnClickListener {
            startActivity(Intent(this, CalendarActivity::class.java))
        }


        binding.home.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}