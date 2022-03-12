package com.example.catroom.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.catroom.R
import kotlinx.android.synthetic.main.activity_home_screeb.*

class HomeScreeb : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screeb)
        btnsclick()
    }

    private fun btnsclick() {
        signinid.setOnClickListener {
            val intent= Intent(this,LoginScreen::class.java)
            startActivity(intent)
            finish()
        }
        signupid.setOnClickListener {
            val intent= Intent(this,SignupScreen::class.java)
            startActivity(intent)
            finish()
        }
    }
}