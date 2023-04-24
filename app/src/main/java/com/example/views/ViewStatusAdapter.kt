package com.example.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.skrive.R

class ViewStatusAdapter(private val viewstatus:ArrayList<ViewApplications>):RecyclerView.Adapter<ViewStatusAdapter.ViewStatusHolder>() {
    private lateinit var vlistener: OnItemClickListener


    interface OnItemClickListener{
        fun onitemClick(position: Int)
    }
    fun setonitemClickListener(listener: OnItemClickListener){
        vlistener=listener

    }


    class ViewStatusHolder(ApplicationView:View, listener:OnItemClickListener):RecyclerView.ViewHolder(ApplicationView){
        val date:TextView = ApplicationView.findViewById(R.id.dateapp)
        val roll:TextView= ApplicationView.findViewById(R.id.approllno)
        init{
            ApplicationView.setOnClickListener {
                listener.onitemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewStatusHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.viewstatusrecycler,parent,false)
        setonitemClickListener(vlistener)
        return ViewStatusHolder(itemView,vlistener)
    }

    override fun getItemCount(): Int {
        return viewstatus.size
    }

    override fun onBindViewHolder(holder: ViewStatusHolder, position: Int) {
        val currentitem = viewstatus[position]
        holder.date.text =currentitem.dateapp.toString()
        holder.roll.text=currentitem.strollno.toString()


    }
}