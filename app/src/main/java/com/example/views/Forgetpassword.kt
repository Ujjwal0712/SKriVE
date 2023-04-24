package com.example.views


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.skrive.R
import com.example.skrive.databinding.ActivityForgetpasswordBinding
import com.google.firebase.auth.FirebaseAuth

class Forgetpassword : AppCompatActivity() {
    private lateinit var binding: ActivityForgetpasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_SKriVE)
        binding= ActivityForgetpasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.backtologin.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }

        binding.submit.setOnClickListener {
            val em=binding.email.text.toString().trim { it <= ' '}

            if (em.isEmpty()){
                Toast.makeText(
                    this,
                    "Please enter you email",
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                FirebaseAuth.getInstance().sendPasswordResetEmail(em)
                    .addOnCompleteListener{task ->
                        if(task.isSuccessful){
                            Toast.makeText(
                                this,
                                "Email is sent successfully with reset password link",
                                Toast.LENGTH_LONG
                            ).show()
                            finish()
                        }
                    }
            }
        }
    }
}


