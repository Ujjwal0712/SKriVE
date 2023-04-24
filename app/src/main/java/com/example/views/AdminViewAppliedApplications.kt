package com.example.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skrive.R
import com.google.firebase.database.*

class AdminViewAppliedApplications : AppCompatActivity() {
    private lateinit var db: DatabaseReference
    private lateinit var ApplicationRecyclerView: RecyclerView
    private lateinit var ApplicationArrayList:ArrayList<Applications>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_SKriVE)
        setContentView(R.layout.activity_admin_view_applied_applications)

        ApplicationRecyclerView=findViewById(R.id.recycler)
        ApplicationRecyclerView.layoutManager=LinearLayoutManager(this)
        ApplicationRecyclerView.hasFixedSize()
        ApplicationArrayList= arrayListOf<Applications>()
        getApplicationdata()
    }

    private fun getApplicationdata() {
         db= FirebaseDatabase.getInstance().getReference("User")
         db.addValueEventListener(object : ValueEventListener{
             override fun onDataChange(snapshot: DataSnapshot) {
                 if (snapshot.exists()) {
                     for (Applicationsnapshot in snapshot.children) {
                         val item = Applicationsnapshot.getValue(Applications::class.java)
                         ApplicationArrayList.add(item!!)

                     }
                     val adapter=ApplicationAdapter(ApplicationArrayList)
                     ApplicationRecyclerView.adapter = adapter

                     adapter.setonitemClickListener(object: ApplicationAdapter.OnItemClickListener{
                         override fun onitemClick(position: Int) {
                             val intent= Intent( applicationContext, ViewForm::class.java)
                             intent.putExtra("strollno",ApplicationArrayList[position].strollno)
                             startActivity(intent)

                         }

                     })

                     }
                 }

             override fun onCancelled(error: DatabaseError) {
                 TODO("Not yet implemented")
             }

         })


}
}




