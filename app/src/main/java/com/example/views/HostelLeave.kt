package com.example.views

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.skrive.R
import com.example.skrive.databinding.ActivityHostelLeaveBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HostelLeave : AppCompatActivity() {
    private lateinit var binding: ActivityHostelLeaveBinding
    private lateinit var database: DatabaseReference
    private lateinit var namest: TextView
    private lateinit var stroll: TextView
    private lateinit var stcontact: TextView
    private lateinit var staddr:TextView
    private lateinit var homeact:ImageView
    private lateinit var cdact:ImageView
    private var db = Firebase.firestore
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_SKriVE)
        binding= ActivityHostelLeaveBinding.inflate(layoutInflater)
        setContentView(binding.root)
        namest=findViewById(R.id.namest)
        stroll=findViewById(R.id.stroll)
        stcontact=findViewById(R.id.stcontact)
        staddr=findViewById(R.id.staddr)
        homeact=findViewById(R.id.homeact)
        cdact=findViewById(R.id.cdact)

        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        val ref= db.collection("user").document(userId)


        homeact.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
        cdact.setOnClickListener {
            startActivity(Intent(this, CalendarActivity::class.java))
        }

        ref.get().addOnSuccessListener{
            if(it !=null){
                val name= it.data?.get("Name").toString()
                val roll= it.data?.get("Roll No").toString()
                val cont= it.data?.get("contact").toString()
                val addr= it.data?.get("address").toString()
                val econtact= it.data?.get("econtact").toString()
                namest.text=name
                stroll.text=roll
                stcontact.text=("$cont, $econtact")
                staddr.text=addr
            }
        }

            .addOnFailureListener{
                Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show()
            }
        binding.sbutton.setOnClickListener {
            ref.get().addOnSuccessListener{
                if(it !=null){
                    val name= it.data?.get("Name").toString()
                    val roll= it.data?.get("Roll No").toString()
                    val cont= it.data?.get("contact").toString()
                    val addr= it.data?.get("address").toString()
                    val econtact= it.data?.get("econtact").toString()


                    val stname=name
                    val strollno=roll
                    val pcontact=cont
                    val staddr=addr
                    val rh= binding.rh.text.toString()
                    val dateapp=binding.dateapp.text.toString()
                    val purleave=binding.purleave.text.toString()
                    val datedep=binding.datedep.text.toString()
                    val timedep=binding.timedep.text.toString()
                    val datearr=binding.datearr.text.toString()
                    val timearr=binding.timearr.text.toString()
                    val days=binding.days.text.toString()

                    val emcontact=econtact

                    database=FirebaseDatabase.getInstance().getReference("User")
                    val user= User(dateapp,stname,strollno,rh,purleave,datedep,timedep,datearr,timearr,days,pcontact,emcontact,staddr)
                    database.child(strollno).setValue(user).addOnSuccessListener {
                        binding.dateapp.text.clear()
                        binding.purleave.text.clear()
                        binding.datedep.text.clear()
                        binding.timedep.text.clear()
                        binding.datearr.text.clear()
                        binding.timearr.text.clear()
                        binding.days.text.clear()
                        binding.rh.text.clear()
                        Toast.makeText(this, "Successfully applied",Toast.LENGTH_SHORT).show()

                    }.addOnFailureListener{
                        Toast.makeText(this, "Failed",Toast.LENGTH_SHORT).show()
                    }


                }
            }

        }

    }



}