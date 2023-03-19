package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class ProdDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prod_detail)

        val img1 = findViewById<ImageView>(R.id.img1)
        val img2 = findViewById<ImageView>(R.id.img2)
        val text1 =  findViewById<TextView>(R.id.t1)
        val text2 =  findViewById<TextView>(R.id.t2)
        val text3 =  findViewById<TextView>(R.id.t3)

        img1.setImageResource(intent.getIntExtra("img1" , 0))
        img2.setImageResource(intent.getIntExtra("img2" , 0))
        text1.text = intent.getStringExtra("text1")
        text2.text = intent.getStringExtra("text2")
        text3.text = intent.getStringExtra("text3")

        val arrow = findViewById<ImageView>(R.id.backbtn)
        arrow.setOnClickListener{
            finish()
        }
    }
}