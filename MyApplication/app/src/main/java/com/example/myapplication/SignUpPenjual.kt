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

class SignUpPenjual : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_penjual)

        val db = Firebase.firestore

        val Nama = findViewById<EditText>(R.id.NamaPR)
        val Password = findViewById<EditText>(R.id.PasswordPR)
        val Regist = findViewById<Button>(R.id.SUP)
        val Nu = findViewById<TextView>(R.id.NamaUser)
        val AList=ArrayList<Long>()
        val sharedPref =  getSharedPreferences("LogIn", Context.MODE_PRIVATE)
        val text = sharedPref.getString("usernameP", "")
        Nu.setText(text)
        var owner=text


        Regist.setOnClickListener {
            val retrieveData = db.collection("User")
                .get()
                .addOnSuccessListener { result->
                    val dataInput = DataSeller(Nama.text.toString(), Password.text.toString(),0.0,0,0,AList, 0,owner.toString())
                    db.collection("Seller").document(Nama.text.toString()).set(dataInput)
                    val intents = Intent(this@SignUpPenjual, LoginPenjual::class.java)
                    startActivity(intents)
                }
        }
    }
}
