package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

private lateinit var email : EditText
private lateinit var password : EditText




class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById<EditText>(R.id.emailIntput)
        password = findViewById<EditText>(R.id.passwordInput)
        var loginButton = findViewById<Button>(R.id.loginButton)
        var register = findViewById<TextView>(R.id.register)
        var checkBox = findViewById<CheckBox>(R.id.checkBox)


        val sharedPreferences: SharedPreferences =
            getSharedPreferences("Login", Context.MODE_PRIVATE)

        loginButton.setOnClickListener {
            if (email.getText().isNotEmpty() && password.getText().length > 0
            ) {

                val sh = getSharedPreferences("Register", MODE_PRIVATE)
                val regEmail = sh.getString("email", "")
                val regPassword = sh.getString("password","")
                val emailInput = email?.getText().toString();
                val passwordInput = password.getText().toString();
                val editor: SharedPreferences.Editor = sharedPreferences.edit();
                if(regEmail == emailInput && regPassword == passwordInput){
                    editor.putString("email", emailInput);
                    editor.putString("password", passwordInput);
                    editor.putBoolean("state", true);
                    editor.commit();

                    val intent = Intent(this,LandingPage::class.java)
                    startActivity(intent)
                }
                else{
                    val toastMessage = "Enterd field dosen't match"
                    Toast.makeText(applicationContext, toastMessage, Toast.LENGTH_SHORT).show()
                }

//                val toastMessage = "Email: " + email.getText().toString()
//                Toast.makeText(applicationContext, toastMessage, Toast.LENGTH_SHORT).show()
//                val toastMessage = "Email: " + inp1
//                Toast.makeText(applicationContext, toastMessage, Toast.LENGTH_SHORT).show()

            } else {
                val toastMessage = "Username or Password are not populated"
                Toast.makeText(applicationContext, toastMessage, Toast.LENGTH_SHORT).show()

            }
        }

        register.setOnClickListener{
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }


    }

        override fun onResume(){
            super.onResume()
            val sh = getSharedPreferences("Login", MODE_PRIVATE)
            val inp1 = sh.getString("email", "")
            val inp2 = sh.getString("password","")
            email.setText(inp1)
            password.setText(inp2.toString())
        }

}