package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home2)
        val  RV = findViewById<ListView>(R.id.RV)
        var data = mutableListOf<String>()
        val db = Firebase.firestore
        db.collection("Seller")
            .get()
            .addOnSuccessListener{result ->
                for (document in result){
                    data.add(document.id)
                }

            }


    }
}