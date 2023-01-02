package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class editprofile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editprofile)
        val db = Firebase.firestore
        val ENama= findViewById<TextView>(R.id.GNama)
        val EPass= findViewById<EditText>(R.id.GPassword)
        val EChange= findViewById<Button>(R.id.Change)
        val sharedPref =  getSharedPreferences("LogIn", Context.MODE_PRIVATE)
        val text = sharedPref.getString("usernameP", "")
        var transaction= ArrayList<Long>()
        ENama.setText(text)
        Log.d("Self",text.toString())
        EChange.setOnClickListener {
            if (ENama.text.toString()==text){
                db.collection("User")
                    .get()
                    .addOnSuccessListener {result->
                        for (document in result){
                            transaction= document.get("transaksiid") as ArrayList<Long>
                        }
                        val datainput =Data(ENama.text.toString(),EPass.text.toString(),transaction)
                        db.collection("User").document(ENama.text.toString()).set(datainput)
                        val intent =Intent(this@editprofile,editprofile::class.java)
                        startActivity(intent)
                    }
            }


        }
    }
}