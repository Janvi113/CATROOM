package com.example.catroom.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.catroom.R
import com.example.catroom.activity.ChatScreen
import com.example.catroom.model.User

class myAdapter(val context: Context,val userlist:ArrayList<User>):RecyclerView.Adapter<myAdapter.myviewholder>() {
    class myviewholder(itemView: View) :RecyclerView.ViewHolder(itemView){
      val txt=itemView.findViewById<TextView>(R.id.usernametxt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {
      val view=LayoutInflater.from(parent.context).inflate(R.layout.chatitems,parent,false)
        return myviewholder(view)
    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {
        val data=userlist[position]
        holder.txt.text=data.name
        holder.itemView.setOnClickListener {
            val intent= Intent(context, ChatScreen::class.java)
            intent.putExtra("name",data.name)
            intent.putExtra("uid",data.uid)
            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
     return userlist.size
    }
}