package com.example.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.skrive.R

class CalendarActivity : AppCompatActivity() {
    private lateinit var home: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_SKriVE)
        setContentView(R.layout.activity_calendar)

        home=findViewById(R.id.home)

        home.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}