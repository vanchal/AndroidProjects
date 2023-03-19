package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

//        binding.quoteText.text = "Do, or do not. There is no try"
//        binding.quoteAuthor.text = "Yoda"


        val quoteObj = Quote("Do, or do not. There is no try","Yoda" )

        binding.quote = quoteObj
    }
}