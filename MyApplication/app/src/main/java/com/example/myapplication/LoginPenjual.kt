package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.room.Database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class LoginPenjual : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_penjual)

        val db = Firebase.firestore

        val Nama = findViewById<EditText>(R.id.NamaPL)
        val Pass = findViewById<EditText>(R.id.PasswordPL)
        val sp = findViewById<Button>(R.id.loginP)
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        sp.setOnClickListener {
            val retrieveData = db.collection("Seller")
                .get()
                .addOnSuccessListener { result->
                    CoroutineScope(Dispatchers.IO).async {
                        for (document in result) {
                            if (document.data.get("nama") == Nama.text.toString()) {
                                Log.d("HAHA", "True")
                                if (document.data.get("pass") == Pass.text.toString()) {
                                    Log.d("HAHA", "True")
                                    with(sharedPref.edit()) {
                                        putString("username", Nama.text.toString())
                                        putString("password", Pass.text.toString())
                                        apply()
                                        val eIntent = Intent(this@LoginPenjual, Toko::class.java)
                                        startActivity(eIntent)
                                    }
                                }
                            }
                        }
                    }
                }
        }
    }
}