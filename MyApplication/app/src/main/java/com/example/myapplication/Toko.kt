package com.example.myapplication

// tampilkan toko yang benar + ngambil rating

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Toko : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toko)

        val nama = findViewById<TextView>(R.id.textView)
        val stock = findViewById<EditText>(R.id.JumlahStock)
        val harga = findViewById<EditText>(R.id.HargaTransaksi)
        val update = findViewById<Button>(R.id.update)
        val listTransaksi = findViewById<Button>(R.id.ListTransaksi)
        val Alist =ArrayList<Long>()
        val sharedPref =  getSharedPreferences("LoginPenjual", Context.MODE_PRIVATE)
        val text = sharedPref.getString("username", "")
        val textpass= sharedPref.getString("password", "")
        val  rating = findViewById<TextView>(R.id.textView2)
        val db = Firebase.firestore
        val retrieveData = db.collection("Seller")
        nama.setText(text)
        if(text != null){
            db.collection("Seller")
                .document(text)
                .get()
                .addOnSuccessListener{ result ->
                    rating.setText(result.data?.get("rating").toString())
                }
        }


        listTransaksi.setOnClickListener {
            val intents = Intent(this@Toko, CompleteTransaction::class.java)
            startActivity(intents)
        }
        update.setOnClickListener {
            Log.d("Test",text.toString())
            val h1=harga.text.toString().toInt()
            val h2=stock.text.toString().toInt()
            db.collection("Seller")
                .get()
                .addOnSuccessListener { result ->
                    Log.d("Test", text.toString())
                    if(h2>0 && h1>0){
                        for (document in result) {
                            Log.d("Test", text.toString())
                            if (text == document.data.get("nama")) {
                                Log.d("Test", h2.toString())
                                Log.d("Test", h1.toString())
                                val dataInput =
                                    DataSeller(text.toString(), textpass.toString(), 0.0, h2, h1,Alist)
                                db.collection("Seller").document(text.toString()).set(dataInput)
                                val intents = Intent(this@Toko, MainActivity2::class.java)
                                startActivity(intents)
                            } else {

                            }
                        }
                    }else{
                        Toast.makeText(this@Toko,"Input Stock / Harga Terlalu Kecil",
                            Toast.LENGTH_LONG).show()
                    }

                }
        }

    }
}