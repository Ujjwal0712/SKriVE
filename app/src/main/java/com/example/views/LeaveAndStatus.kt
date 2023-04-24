package com.example.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.skrive.R
import com.example.skrive.databinding.ActivityLeaveAndStatusBinding
import com.example.skrive.databinding.ActivityViewFormBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LeaveAndStatus : AppCompatActivity() {
    private lateinit var binding: ActivityLeaveAndStatusBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_SKriVE)
        binding = ActivityLeaveAndStatusBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setstatus()
        setform()

    }

    private fun setform() {
        database = FirebaseDatabase.getInstance().getReference("User")
        val bundle: Bundle? = intent.extras
        val roll= bundle!!.getString("strollno")
        if (roll != null) {
            database.child(roll).get().addOnSuccessListener {
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

    }

    private fun setstatus() {
        database = FirebaseDatabase.getInstance().getReference("Status")
        val bundle: Bundle? = intent.extras
        val roll = bundle!!.getString("strollno")
        if (roll != null) {
            database.child(roll).get().addOnSuccessListener {
                if (it.exists()) {
                    val stat = it.child("application").value
                    binding.status.text = stat.toString()
                }

            }
        }

    }
}