package com.example.views

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.extensions.extensions.toast
import com.example.skrive.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ViewStatus : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var ViewStatusRecyclerView: RecyclerView
    private lateinit var ViewStatusArrayList:ArrayList<ViewApplications>
    private var db = Firebase.firestore
    private lateinit var rollno:String

    val userId = FirebaseAuth.getInstance().currentUser!!.uid
    val ref= db.collection("user").document(userId)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_SKriVE)

        setContentView(R.layout.activity_view_status)


        ViewStatusRecyclerView=findViewById(R.id.viewstatusrecyclerview)
        ViewStatusRecyclerView.layoutManager= LinearLayoutManager(this)
        ViewStatusRecyclerView.hasFixedSize()
        ViewStatusArrayList= arrayListOf<ViewApplications>()
        fetchdata()
    }

    private fun fetchdata() {
        ref.get().addOnSuccessListener {
            if(it != null){
                rollno= it.data?.get("Roll No")?.toString().toString()
            }
            database=FirebaseDatabase.getInstance().getReference("User")
            val query = database.orderByChild("strollno").equalTo(rollno)

            query.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // Clear the existing user list
                    // Iterate through the snapshot to populate the user list
                    for (snapshot in dataSnapshot.children) {
                        val user = snapshot.getValue(ViewApplications::class.java)
                        if (user != null) {
                            ViewStatusArrayList.add(user)

                        }
                        val adapter=ViewStatusAdapter(ViewStatusArrayList)
                        ViewStatusRecyclerView.adapter = adapter

                        adapter.setonitemClickListener(object: ViewStatusAdapter.OnItemClickListener {
                            override fun onitemClick(position: Int) {
                                val intent = Intent(applicationContext, LeaveAndStatus::class.java)
                                intent.putExtra("dateapp", ViewStatusArrayList[position].dateapp)
                                intent.putExtra("strollno", ViewStatusArrayList[position].strollno)
                                startActivity(intent)

                            }


                        })
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    toast("failed!!")
                }


            })
        }
    }

}



