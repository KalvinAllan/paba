package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Ratingpage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ratingpage)

        // BELUM BISA NYARI TOKO YG BENER
        val Komentar=findViewById<EditText>(R.id.Komentar)
        val rating=findViewById<EditText>(R.id.ratingS)
        val update=findViewById<Button>(R.id.Ups)
        val sharedPref =  getSharedPreferences("LogIn", Context.MODE_PRIVATE)
        val text = sharedPref.getString("usernameP", "")
        val sharedPref2 =  getSharedPreferences("LoginPenjual", Context.MODE_PRIVATE)
        val text2 = sharedPref.getString("username", "")
        val db = Firebase.firestore
        val rater=ArrayList<String>()
        val star=ArrayList<Int>()
        val komen=ArrayList<String>()
        val text3="e"

        update.setOnClickListener {
            rater.add(text.toString())
            star.add(rating.text.toString().toInt())
            komen.add(Komentar.text.toString())
            Log.d("haha", rater.toString())
            Log.d("haha", star.toString())
            Log.d("haha", komen.toString())
            val dataInput = Rating(rater,star,komen)
            db.collection("Rating").document(text3.toString()).set(dataInput)
//            val intents = Intent(this@Ratingpage, MainActivity::class.java)
//            startActivity(intents)
        }
    }
}