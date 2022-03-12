package com.example.catroom.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catroom.R
import com.example.catroom.adapter.chatAdapter
import com.example.catroom.model.MessegeList
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_chat_screen.*

class ChatScreen : AppCompatActivity() {
    lateinit var chatAdapter: chatAdapter
    lateinit var db:DatabaseReference
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var ChatList:ArrayList<MessegeList>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_screen)
        var SENT_ROOM:String?=null
        var RECIEVE_ROOM:String?=null
        ChatList= ArrayList()
        firebaseAuth= FirebaseAuth.getInstance()
        db=FirebaseDatabase.getInstance().getReference()
        val username=intent.getStringExtra("name")
        usernameli.setText(username)
        val recieveruid=intent.getStringExtra("uid")
        val sentuid=firebaseAuth.currentUser?.uid
        SENT_ROOM=recieveruid+sentuid
        RECIEVE_ROOM=sentuid+recieveruid
        chatrecylerview.apply {
            chatAdapter=chatAdapter(this@ChatScreen,ChatList)
            adapter=chatAdapter
            layoutManager=LinearLayoutManager(this@ChatScreen)

        }
        db.child("chats").child(SENT_ROOM!!).child("messege")
            .addValueEventListener(object :ValueEventListener{
                @SuppressLint("NotifyDataSetChanged")
                override fun onDataChange(snapshot: DataSnapshot) {

                    ChatList.clear()
                    for (positionsnapshots in snapshot.children){
                        val currentuser=positionsnapshots.getValue(MessegeList::class.java)
                        ChatList.add(currentuser!!)
                    }
                    chatAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
        sendid.setOnClickListener {
            val messege=tying.text.toString()
            val messegeobject=MessegeList(messege,sentuid)
            db.child("chats").child(SENT_ROOM!!).child("messege")
                .push().setValue(messegeobject)
                .addOnSuccessListener {
                    db.child("chats").child(RECIEVE_ROOM!!).child("messege")
                        .push().setValue(messegeobject)
                }
            tying.setText("")

        }
    }
}