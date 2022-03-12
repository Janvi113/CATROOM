package com.example.catroom.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.catroom.MainActivity
import com.example.catroom.R
import com.example.catroom.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_home_screeb.*
import kotlinx.android.synthetic.main.activity_signup_screen.*

class SignupScreen : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var db:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_screen)
        firebaseAuth= FirebaseAuth.getInstance()
        signup.setOnClickListener {
        val name=edtname.text.toString()
        val email=edtemail.text.toString()
        val password=edtpassword.text.toString()
        val confirmpassword=edtconfirmpassword.text.toString()
        setupfirebase(name,email,password,confirmpassword)
        }
        btnclick()
    }

    private fun setupfirebase(name: String, email: String, password: String, confirmpassword: String) {

        if (password!=confirmpassword){
            Toast.makeText(this, "password should be matched", Toast.LENGTH_SHORT).show()
        }
        if (email.isBlank()&&password.isBlank()){
            Toast.makeText(this, "email and password should be filled", Toast.LENGTH_SHORT).show()
        }

            firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        adduserinterface(name,email,firebaseAuth.currentUser?.uid!!)
                        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
                        val intent=Intent(this,MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else{
                        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                    }
                }


    }

    private fun adduserinterface(name: String, email: String, uid: String?) {
      db=FirebaseDatabase.getInstance().getReference()

            db.child("user").child(uid!!).setValue(User(name, email, uid))

    }


    private fun btnclick() {
        backbtn.setOnClickListener {

                val intent= Intent(this,HomeScreeb::class.java)
                startActivity(intent)
                finish()

        }
        signupclick.setOnClickListener {
                val intent= Intent(this,LoginScreen::class.java)
                startActivity(intent)
                finish()
            }


    }
}