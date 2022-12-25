package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val db = Firebase.firestore


        val daftar = findViewById<Button>(R.id.SellerUp)
        val Pass = findViewById<Button>(R.id.Buy)
        val Login = findViewById<Button>(R.id.SellerIn)

        Login.setOnClickListener {
            Log.d("Nama","5")
            val intent = Intent(this@MainActivity2,LoginPenjual::class.java)
            startActivity(intent)
        }

        daftar.setOnClickListener {
            val intent = Intent(this@MainActivity2,SignUpPenjual::class.java)
            startActivity(intent)
            Log.d("Nama","3")
        }

        Pass.setOnClickListener {
            val intent = Intent(this@MainActivity2,Home::class.java)
            startActivity(intent)
            Log.d("Nama", "4")
        }

    }
}