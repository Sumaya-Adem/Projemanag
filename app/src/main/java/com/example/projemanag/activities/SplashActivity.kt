package com.example.projemanag.activities

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.TextView
import com.example.projemanag.R
import com.example.projemanag.firebase.FirestoreClass
import com.google.firebase.firestore.FirebaseFirestore

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        val tvTitleName: TextView = findViewById(R.id.app_title)
        val typeFace: Typeface = Typeface.createFromAsset(assets,
            "HanringtonDemoRegular.ttf")
        tvTitleName.typeface = typeFace

        Handler().postDelayed({
            var currentUserID = FirestoreClass().getCurrentUserId()
            if(currentUserID.isNotEmpty()){
                startActivity(Intent(this, MainActivity::class.java))
            }else{
                startActivity(Intent(this, IntroActivity::class.java))
            }
            finish()
        }, 2500)

    }
}