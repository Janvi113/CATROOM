package com.example.catroom.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.catroom.MainActivity
import com.example.catroom.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        firebaseAuth= FirebaseAuth.getInstance()
        if (firebaseAuth.currentUser?.uid!=null){
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        else{
            val intent=Intent(this,HomeScreeb::class.java)
            startActivity(intent)
            finish()
        }
        Handler(Looper.getMainLooper()).postDelayed({
           progressbar.visibility=View.GONE
           val intent=Intent(this,HomeScreeb::class.java)
           startActivity(intent)
           finish()
        },3000)
    }
}