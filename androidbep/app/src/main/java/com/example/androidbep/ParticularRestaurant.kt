package com.example.androidbep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class ParticularRestaurant : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_particular_restaurant)

        val resimg = findViewById<ImageView>(R.id.img_main)
        val resname = findViewById<TextView>(R.id.main_text)

        resimg.setImageResource(intent.getIntExtra("img" , 0))
        resname.text = intent.getStringExtra("name")

        val arrow = findViewById<ImageView>(R.id.backbtn)
        arrow.setOnClickListener{
            finish()
        }

    }
}