package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class LogIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        val nama = findViewById<EditText>(R.id.NamaL)
        val password = findViewById<EditText>(R.id.PasswordL)
        val Login = findViewById<Button>(R.id.login)
        val db = Firebase.firestore
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        Login.setOnClickListener {
            val retrieveData = db.collection("User")
                .get()
                .addOnSuccessListener { result->
                    CoroutineScope(Dispatchers.IO).async {
                        for (document in result){
                            if (nama.text.toString()==document.data.get("nama")){
                                Log.d("Nama","1")
                                if (password.text.toString()==document.data.get("pass")){
                                    Log.d("Nama", "2")
                                    with(sharedPref.edit()) {
                                        putString("usernameP", nama.text.toString())
                                        apply()
                                        val intent = Intent(this@LogIn,MainActivity2::class.java)
                                        startActivity(intent)
                                    }
                                }
                            }
                        }
                    }

                }
        }

    }
}