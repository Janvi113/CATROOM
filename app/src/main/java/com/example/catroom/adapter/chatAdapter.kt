package com.example.catroom.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.catroom.R
import com.example.catroom.model.MessegeList
import com.google.firebase.auth.FirebaseAuth

class chatAdapter(val context: Context,val chatlist:ArrayList<MessegeList>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val Receiver_Items=1
    val SENT_Items=2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType==2){
            val view= LayoutInflater.from(parent.context).inflate(R.layout.sent,parent,false)
            return SendViewholder(view)
        }
        else{
            val view=LayoutInflater.from(parent.context).inflate(R.layout.recieve,parent,false)
            return RecieveViewholder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentpostion=chatlist[position]
        if (FirebaseAuth.getInstance().currentUser?.uid.equals(currentpostion.senderId)){
            return SENT_Items
        }
        else{
            return Receiver_Items
        }

    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val curentposition=chatlist[position]
        if (holder.javaClass==SendViewholder::class.java){
            val viewholder=holder as SendViewholder
            holder.senttxt.text=curentposition.messegeId
        }else{
            val viewholder=holder as RecieveViewholder
            holder.recievetxt.text=curentposition.messegeId
        }
    }

    override fun getItemCount(): Int {
      return chatlist.size
    }

    class SendViewholder(itemView: View) :RecyclerView.ViewHolder(itemView){
       val senttxt=itemView.findViewById<TextView>(R.id.senttxt)
    }
    class RecieveViewholder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val recievetxt=itemView.findViewById<TextView>(R.id.recievetxt)

    }
}