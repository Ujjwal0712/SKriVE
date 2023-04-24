package com.example.views

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.extensions.extensions.toast
import com.example.skrive.R
import com.example.skrive.databinding.ActivityLoginBinding
import com.example.utils.fbutils.firebaseAuth

/** fix missing im ports **/




class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var signInEmail: String
    private lateinit var signInPassword: String
    private lateinit var signInInputsArray: Array<EditText>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_SKriVE)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.adminlogin.setOnClickListener {
            startActivity(Intent(this, AdminLogin::class.java))
        }


        signInInputsArray = arrayOf(binding.email, binding.password)
        binding.register.setOnClickListener {
            startActivity(Intent(this, Createaccount::class.java))
            finish()
        }

        binding.login.setOnClickListener {
            signInUser()
        }

        binding.forgetpassword.setOnClickListener{
            startActivity(Intent(this, Forgetpassword::class.java))
        }
    }

    private fun notEmpty(): Boolean = signInEmail.isNotEmpty() && signInPassword.isNotEmpty()

    private fun signInUser() {
        signInEmail = binding.email.text.toString().trim()
        signInPassword = binding.password.text.toString().trim()
        if(signInEmail != "admin@iiitu.ac.in"){
            if (notEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(signInEmail, signInPassword)
                    .addOnCompleteListener { signIn ->
                        if (signIn.isSuccessful) {
                            val intent = Intent(this, HomeActivity::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                            toast("signed in successfully")
                            finish()
                        }
                         else {
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
            toast("You are a Admin, Login through AdminLogin")
        }


    }

}
