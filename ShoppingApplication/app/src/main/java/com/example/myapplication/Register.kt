package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var email = findViewById<EditText>(R.id.emailIntput)
        var name = findViewById<EditText>(R.id.nameInput)
        var password = findViewById<EditText>(R.id.passwordInput)
        var confirmPassword = findViewById<EditText>(R.id.confirmPasswordInput)
        var registerButton = findViewById<Button>(R.id.regButton)
        var loginText = findViewById<TextView>(R.id.loginText)

        val sharedPreferences: SharedPreferences =
            getSharedPreferences("Register", Context.MODE_PRIVATE)

        registerButton.setOnClickListener {
            if (email.getText().length > 0 && name.getText().length > 0 && password.getText().length > 0
                && confirmPassword.getText().length > 0 && password.getText().toString() == confirmPassword.getText().toString()
            ) {
                val emailInput = email.getText().toString();
                val passwordInput = password.getText().toString();
                val nameInput = name.getText().toString();
                val editor: SharedPreferences.Editor = sharedPreferences.edit();
                editor.putString("email", emailInput);
                editor.putString("name", nameInput);
                editor.putString("password", passwordInput);
                editor.commit();
                val toastMessage = "User Registered "
                Toast.makeText(applicationContext, toastMessage, Toast.LENGTH_SHORT).show()

                val intent = Intent(this,Login::class.java)
                startActivity(intent)

            } else {
                val toastMessage = "Please enter all fields correctly!!"
                Toast.makeText(applicationContext, toastMessage, Toast.LENGTH_SHORT).show()

            }

        }

        loginText.setOnClickListener{
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
        }

    }
}