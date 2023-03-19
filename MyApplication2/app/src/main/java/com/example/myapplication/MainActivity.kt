package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
//    var count : Int = 0
    lateinit var mainViewModel : MainViewModel
    lateinit var textCount : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(10)).get(MainViewModel::class.java)

        textCount = findViewById<TextView>(R.id.count)
        setText()
    }

    fun setText(){
        textCount.text = mainViewModel.count.toString()
    }

    fun increment(v : View){
        mainViewModel.increment()
        setText()
    }
}