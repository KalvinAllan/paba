package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LogIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        val nama = findViewById<EditText>(R.id.NamaL)
        val password = findViewById<EditText>(R.id.PasswordL)
        val Login = findViewById<Button>(R.id.login)
        val db = Firebase.firestore
        Login.setOnClickListener {
            val retrieveData = db.collection("User")
                .get()
                .addOnSuccessListener { result->
                    for (document in result){
                        if (nama.text.toString()==document.data.get("nama")){
                            Log.d("Nama","1")
                            if (password.text.toString()==document.data.get("pass")){
                                Log.d("Nama", "2")
                                val intent = Intent(this@LogIn,MainActivity2::class.java)
                                startActivity(intent)
                            }
                        }
                    }
                }
        }

    }
}