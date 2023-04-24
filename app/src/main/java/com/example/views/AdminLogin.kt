package com.example.views

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.extensions.extensions.toast
import com.example.skrive.R
import com.example.skrive.databinding.ActivityAdminLoginBinding
import com.example.utils.fbutils

class AdminLogin : AppCompatActivity() {
    private lateinit var binding: ActivityAdminLoginBinding
    private lateinit var signInEmail: String
    private lateinit var signInPassword: String
    private lateinit var signInInputsArray: Array<EditText>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_SKriVE)
        binding = ActivityAdminLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.adminloginbtn.setOnClickListener {
            signInUser()

        }
        binding.adforgetpassword.setOnClickListener{
            startActivity(Intent(this, Forgetpassword::class.java))
        }

        binding.studentlogin.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }
    }

        private fun notEmpty(): Boolean = signInEmail.isNotEmpty() && signInPassword.isNotEmpty()

        private fun signInUser() {
            signInEmail = binding.adminemail.text.toString().trim()
            signInPassword = binding.adminpassword.text.toString().trim()
            if (signInEmail == "admin@iiitu.ac.in") {
                if (notEmpty()) {
                    fbutils.firebaseAuth.signInWithEmailAndPassword(signInEmail, signInPassword)
                        .addOnCompleteListener { signIn ->
                            if (signIn.isSuccessful) {
                                // Start AdminActivity from LoginActivity
                                val intent = Intent(this, AdminHome::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)
                                toast("signed in successfully")
                            } else {
                                toast("sign in failed")
                            }
                        }
                } else {
                    signInInputsArray.forEach { input ->
                        if (input.text.toString().trim().isEmpty()) {
                            input.error = "${input.hint} is required"
                        }
                    }
                }


            }else{
                toast("Authentication failed")
            }
        }

}