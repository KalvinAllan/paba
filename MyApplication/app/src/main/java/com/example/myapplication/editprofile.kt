package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class editprofile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editprofile)
        val db = Firebase.firestore
        val ENama= findViewById<EditText>(R.id.GNama)
        val EPass= findViewById<EditText>(R.id.GPassword)
        val EChange= findViewById<Button>(R.id.Change)
        val sharedPref =  getSharedPreferences("LogIn", Context.MODE_PRIVATE)
        val text = sharedPref.getString("usernameP", "")
        Log.d("Self",text.toString())
        EChange.setOnClickListener {
            db.collection("User").document(text.toString())
                .get()
                .addOnSuccessListener {
                    
                }

        }
    }
}