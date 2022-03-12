package com.example.catroom.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.catroom.MainActivity
import com.example.catroom.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login_screen.*
import kotlinx.android.synthetic.main.activity_signup_screen.*

class LoginScreen : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        firebaseAuth= FirebaseAuth.getInstance()
        signin.setOnClickListener {
            val email=emailtxt.text.toString()
            val password=passwordtxt.text.toString()
            if (email.isBlank()&&password.isBlank()){
                Toast.makeText(this, "email and password should be filled", Toast.LENGTH_SHORT).show()
            }
            Loginup(email,password)
        }

        btnclick1()
    }

    fun Loginup(email: String, password: String) {

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener (this){
            if (it.isSuccessful){
                val intent=Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            }
                else{
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun btnclick1() {
        backbtn1.setOnClickListener {

            val intent= Intent(this,HomeScreeb::class.java)
            startActivity(intent)
            finish()

        }
        signuinclick.setOnClickListener {
            val intent= Intent(this,SignupScreen::class.java)
            startActivity(intent)
            finish()
        }


    }
}