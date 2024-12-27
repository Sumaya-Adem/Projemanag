package com.example.projemanag.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import com.example.projemanag.R

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)


        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        val signUpIntro : Button = findViewById(R.id.btn_sign_up_intro)
        signUpIntro.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        val signInIntro : Button = findViewById(R.id.btn_sign_in_intro)
        signInIntro.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }

    }

}