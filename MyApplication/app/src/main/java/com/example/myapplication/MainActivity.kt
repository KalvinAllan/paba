package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val Login = findViewById<Button>(R.id.In)
        val signUp = findViewById<Button>(R.id.Up)


        signUp.setOnClickListener {
            val intent = Intent(this@MainActivity, SignUp:: class.java)
            startActivity(intent)
        }
        Login.setOnClickListener {
            val intent = Intent(this@MainActivity, Home:: class.java)
            startActivity(intent)
        }
    }
}