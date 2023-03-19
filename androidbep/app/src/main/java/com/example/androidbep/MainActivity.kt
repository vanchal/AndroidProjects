package com.example.androidbep

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("msg",getString(R.string.log_detail))

        var first = findViewById<TextView>(R.id.text1)
        first.textSize = 20f
        first.setTextColor(Color.parseColor("#ff0000"))

        var second = findViewById<TextView>(R.id.text2)
        second.text = "Learn Android"
        second.setTextColor(getColor(R.color.teal_200))
        second.textSize = 14f


        val str1 = intent.getStringExtra("name")
        val int1 = intent.getIntExtra(("age"),0)
        first.text = str1
        second.text = int1.toString()
    }

    override fun onStart() {
        super.onStart()
        Log.i("tag","on Start")
    }

    override fun onStop() {
        super.onStop()
        Log.i("tag","On Stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("tag", "onDestroy")
    }
    override fun onResume() {
        super.onResume()
        Log.i("tag", "onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("tag", "onRestart")
    }
}


