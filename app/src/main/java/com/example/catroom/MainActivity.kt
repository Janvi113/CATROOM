package com.example.catroom

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catroom.adapter.myAdapter
import com.example.catroom.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var db:DatabaseReference
    lateinit var myAdapter: myAdapter
    lateinit var userlist:ArrayList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userlist= ArrayList()
        firebaseAuth= FirebaseAuth.getInstance()
        db=FirebaseDatabase.getInstance().getReference()
        adaptersetup()
    }

    private fun adaptersetup() {
        recyelerview.apply {
            myAdapter= myAdapter(this@MainActivity,userlist)
            adapter=myAdapter
            layoutManager=LinearLayoutManager(this@MainActivity)
        }
        db.child("user").addValueEventListener(object :ValueEventListener{
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {

                userlist.clear()
                for (positionsnapsot in snapshot.children){
                    val userdata=positionsnapsot.getValue(User::class.java)
                    if(firebaseAuth.currentUser?.uid!=userdata?.uid){
                        userlist.add(userdata!!)
                    }
                }
                myAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
        loin.setOnClickListener {
            firebaseAuth.signOut()
        }
    }
}