package com.example.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.skrive.R

class ApplicationAdapter(private val Appl:ArrayList<Applications>):RecyclerView.Adapter<ApplicationAdapter.ApplicationHolder>() {
    private lateinit var alistener: OnItemClickListener


    interface OnItemClickListener{
        fun onitemClick(position: Int)
    }
    fun setonitemClickListener(listener: OnItemClickListener){
        alistener=listener

    }


    class ApplicationHolder(ApplicationView:View, listener:OnItemClickListener):RecyclerView.ViewHolder(ApplicationView){
        val rollno:TextView = ApplicationView.findViewById(R.id.approllno)
        init{
            ApplicationView.setOnClickListener {
                listener.onitemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplicationHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.recyclerviewapplications,parent,false)
        setonitemClickListener(alistener)
        return ApplicationHolder(itemView,alistener)
    }

    override fun getItemCount(): Int {
        return Appl.size
    }

    override fun onBindViewHolder(holder: ApplicationHolder, position: Int) {
        val currentitem = Appl[position]
        holder.rollno.text =currentitem.strollno.toString()


    }
}

