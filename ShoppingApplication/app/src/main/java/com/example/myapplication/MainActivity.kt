package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)
        val sh = getSharedPreferences("Login", MODE_PRIVATE)
        val currentlyLoggedIn = sh.getBoolean("state",false)
        Handler().postDelayed({
            // on below line we are
            // creating a new intent
            if(currentlyLoggedIn == true){
                val intent = Intent(this@MainActivity,LandingPage::class.java)
                startActivity(intent)
            }
            else{
                val intent = Intent(this@MainActivity,Login::class.java)
                startActivity(intent);
            }

//            val i = Intent(
//                this@MainActivity,
//                Register::class.java
//            )
//            startActivity(i)
            finish()
        }, 1000)
    }
}
