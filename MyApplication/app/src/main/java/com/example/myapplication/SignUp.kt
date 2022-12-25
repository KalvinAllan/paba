package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val db = Firebase.firestore

        val Nama = findViewById<EditText>(R.id.NamaR)
        val Pass = findViewById<EditText>(R.id.PasswordR)
        val sp = findViewById<Button>(R.id.SU)
        val Array= arrayListOf<Int>()

        sp.setOnClickListener {
            val dataInput = Data(Nama.text.toString(), Pass.text.toString(),0,Array)
            db.collection("User").document(Nama.text.toString()).set(dataInput)
            val intents = Intent(this@SignUp, MainActivity::class.java)
            startActivity(intents)
        }
    }
}