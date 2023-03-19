package com.example.androidbep

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val fragmentCat = Cat()
        val fragmentDog = Dog()

        val button1 = findViewById<Button>(R.id.btn1)
        val button2 = findViewById<Button>(R.id.btn2)

        button1.setOnClickListener{

//            val intent = Intent(this,MainActivity::class.java)
//            intent.putExtra("name", "Xyz")
//            intent.putExtra("age", 30)
//            startActivity(intent)

//            val intent = Intent(Intent.ACTION_DIAL)
//            intent.data = Uri.parse("tel:0123456789")
//            startActivity(intent)
                supportFragmentManager.fragments.forEach {
                    supportFragmentManager
                        .beginTransaction()
                        .remove(it)
                        .commit()
                }

            supportFragmentManager
                .beginTransaction()
                .add(R.id.frame, fragmentCat)
                .commit()

        }

        button2.setOnClickListener{
//            val intent = Intent(this,MainActivity::class.java)
//            intent.putExtra("name", "Abcde")
//            intent.putExtra("age", 20)
//            startActivity(intent)

//            val intent = Intent(Intent.ACTION_DIAL)
//            intent.data = Uri.parse("tel:0123456789")
//            startActivity(intent)
            supportFragmentManager.fragments.forEach {
                supportFragmentManager
                    .beginTransaction()
                    .remove(it)
                    .commit()
            }

            supportFragmentManager
                .beginTransaction()
                .add(R.id.frame, fragmentDog)
                .commit()

        }


    }

}