package com.example.projemanag.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.projemanag.R
import com.example.projemanag.firebase.FirestoreClass
import com.example.projemanag.models.User
import com.google.firebase.auth.FirebaseAuth

class SignInActivity :BaseActivity() {
    private lateinit var toolBarSingIn: Toolbar
    private lateinit var etEmailSignIn: EditText
    private lateinit var etPasswordSignIn: EditText
    private lateinit var btnSignIn: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        etEmailSignIn = findViewById(R.id.et_email_sign_in)
        etPasswordSignIn = findViewById(R.id.et_password_sign_in)
        btnSignIn = findViewById(R.id.btn_sing_in)
        auth = FirebaseAuth.getInstance()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        toolBarSingIn = findViewById(R.id.toolbar_sign_in)
        setUpActionBar()

        btnSignIn.setOnClickListener {
            singInRegisterUser()
        }
    }
//    public override fun onStart() {
//        super.onStart()
//        // Check if user is signed in (non-null) and update UI accordingly.
//        val currentUser = auth.currentUser
//        if (currentUser != null) {
//            reload()
//        }
//    }

    fun signInSuccess(user: User) {
     hideProgressDialog()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun setUpActionBar(){

        setSupportActionBar(toolBarSingIn)

        val actionBar = supportActionBar
        if(actionBar !=null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24dp)
        }
        toolBarSingIn.setNavigationOnClickListener { onBackPressed() }
    }
   private fun singInRegisterUser(){
       val  email: String = etEmailSignIn.text.toString().trim{ it <= ' '}
       val  password: String = etPasswordSignIn.text.toString().trim{ it <= ' '}

       if(validateForm(email,password)){
           showProgressDialog(resources.getString(R.string.please_wait))

           auth.signInWithEmailAndPassword(email, password)
               .addOnCompleteListener(this) { task ->
                   hideProgressDialog()
                   if (task.isSuccessful) {
                       FirestoreClass().signInUser(this)
                   } else {
                       // If sign in fails, display a message to the user.
                       Log.w("sign in", "createUserWithEmail:failure", task.exception)
                       Toast.makeText(
                           baseContext,
                           "Authentication failed.",
                           Toast.LENGTH_SHORT,
                       ).show()

                   }
               }
       }
   }


    private fun validateForm(email:String,
                             password:String): Boolean{
        return when{
            TextUtils.isEmpty(email)->{
                showErrorSnackBar("Please enter an email address")
                false
            }
            TextUtils.isEmpty(password)->{
                showErrorSnackBar("Please enter a password")
                false
            } else ->{
                true}
        }


    }



}