package com.example.views

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.extensions.extensions.toast
import com.example.skrive.R
import com.example.skrive.databinding.ActivityViewFormBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ViewForm : AppCompatActivity() {
    private lateinit var binding: ActivityViewFormBinding
    private lateinit var database: DatabaseReference
    private var db = Firebase.firestore




    @RequiresApi(Build.VERSION_CODES.P)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_SKriVE)
        binding = ActivityViewFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle: Bundle? = intent.extras

        database = FirebaseDatabase.getInstance().getReference("User")
        val strollno = bundle!!.getString("strollno")
        if (strollno != null) {
            database.child(strollno).get().addOnSuccessListener {
                if (it.exists()) {
                    val dateapp = it.child("dateapp").value
                    val namest = it.child("stname").value
                    val roll = it.child("strollno").value
                    val rh = it.child("rh").value
                    val purleave = it.child("purleave").value
                    val datedep = it.child("datedep").value
                    val timedep = it.child("timedep").value
                    val datearr = it.child("datearr").value
                    val timearr = it.child("timearr").value
                    val days = it.child("days").value
                    val pcontact = it.child("pcontact").value
                    val econtact = it.child("emcontact").value
                    val addr = it.child("staddr").value

                    binding.dateapp.text = dateapp.toString()
                    binding.namest.text = namest.toString()
                    binding.stroll.text = roll.toString()
                    binding.datedep.text = datedep.toString()
                    binding.rh.text = rh.toString()
                    binding.purleave.text = purleave.toString()
                    binding.timedep.text = timedep.toString()
                    binding.datearr.text = datearr.toString()
                    binding.timearr.text = timearr.toString()
                    binding.days.text = days.toString()
                    binding.stcontact.text = ("$pcontact,$econtact").toString()
                    binding.staddr.text = addr.toString()

                }
            }
        }
        binding.approve.setOnClickListener {
            saveDataToFirebase()
        }
        binding.deny.setOnClickListener {
            savetofirebase()
        }

    }

    private fun savetofirebase() {
        database = FirebaseDatabase.getInstance().getReference("Status")
        val stat = Status(application="Not Approved")
        val bundle: Bundle? = intent.extras
        val roll = bundle!!.getString("strollno")
        if (roll != null) {
            database.child(roll).setValue(stat)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Data saved successfully
                        toast("Application Denied")
                    } else {
                        // Failed to save data
                    }
                }
        }


    }


    private fun saveDataToFirebase() {
        database = FirebaseDatabase.getInstance().getReference("Status")
        val stat = Status(application="Approved")
        val bundle: Bundle? = intent.extras
        val roll = bundle!!.getString("strollno")
        if (roll!= null) {
            database.child(roll).setValue(stat)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        toast("Application Approved")
                        // Data saved successfully
                    } else {
                        // Failed to save data
                    }
                }
        }
    }

}





