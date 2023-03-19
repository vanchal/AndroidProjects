package com.example.myapplication

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast

class SingleCartProd : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_cart_prod)

//        val size = resources.getStringArray(R.array.sizes)
//
//        val spinner = findViewById<Spinner>(R.id.spin)
//        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, size)
//        spinner.adapter = adapter
//
//
//        spinner.onItemSelectedListener = object:
//            AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
//                Toast.makeText(this@SingleCartProd, size[position], Toast.LENGTH_SHORT)
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>) {
//                // write code to perform some action
//            }
//        }

    }
}

