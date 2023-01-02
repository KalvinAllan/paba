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
        var namaS=""
        var passS=""
        var ratings=0.0
        var stocks=0
        var hargas=0
        var Rater= ArrayList<String>()
        var komentar=ArrayList<String>()
        var value =ArrayList<Long>()
        var transaksis=ArrayList<Long>()
        var stockOrder=0
        var owner=text
        val db = Firebase.firestore
        val text3=intent.getStringExtra("namatoko")
        var count=0

        var score =0.0

        update.setOnClickListener {
            if (rating.text.toString().toInt()>5 || rating.text.toString().toInt()==0){
                Toast.makeText(this@Ratingpage,"Rate Antara 1-5",
                    Toast.LENGTH_LONG).show()
            }else {
                db.collection("Rating")
                    .get()
                    .addOnSuccessListener { result ->
                        var booleanss = false
                        for (document in result) {
                            if (text3.toString() == document.id) {
                                var a = document.data.get("rater") as ArrayList<String>
                                var b = document.data.get("komentar") as ArrayList<String>
                                var c = document.data.get("value") as ArrayList<Long>
                                for (x in a) {
                                    count = count + 1
                                    Rater.add(x)
                                }
                                for (x in b) {
                                    komentar.add(x)
                                }
                                for (x in c) {
                                    value.add(x)
                                }
                                Rater.add(text.toString())
                                komentar.add(Komentar.text.toString())
                                value.add(rating.text.toString().toLong())
                                Log.d("LOLSL", Rater.toString())
                                Log.d("LOLSL", Rater.size.toString())
                                Log.d("LOLSL", komentar.toString())
                                Log.d("LOLSL", komentar.size.toString())
                                Log.d("LOLSL", value.toString())
                                Log.d("LOLSL", value.size.toString())
                                val dataInput = Rating(Rater, value, komentar)
                                Log.d("MIC", "Masuk1")
                                db.collection("Rating").document(text3.toString()).set(dataInput)
                                Log.d("LOLSL", "YES")
                                booleanss = true
                            }
                        }
                        if (!booleanss){
                            Rater.add(text.toString())
                            komentar.add(Komentar.text.toString())
                            value.add(rating.text.toString().toLong())
                            val dataInput = Rating(Rater, value, komentar)
                            Log.d("MIC", "Masuk1")
                            db.collection("Rating").document(text3.toString()).set(dataInput)
                        }
                        val retrivedata = db.collection("Seller")
                            .get()
                            .addOnSuccessListener { res ->
                                Log.d("LOLSL", "Masuk2")
                                for (document in res) {
                                    if (text3 == document.data.get("nama")) {
                                        Log.d("LOLSL", "Masuk3")
                                        namaS = document.data.get("nama").toString()
                                        passS = document.data.get("pass").toString()
                                        hargas = document.data.get("harga").toString().toInt()
                                        ratings = document.data.get("rating").toString().toDouble()
                                        stocks = document.data.get("stock").toString().toInt()
                                        transaksis = document.data.get("transaksi") as ArrayList<Long>
                                        stockOrder = document.data.get("stockOrder").toString().toInt()
                                        var itung=0
                                        var count2=0.0
                                       for (x in value){
                                           Log.d("LOLSL", x.toString())
                                           count2=count2+x
                                           itung=itung+1
                                       }
                                        Log.d("LOLSL", count2.toString())
                                        ratings=count2/itung
                                        Log.d("LOLSL", count.toString())
                                        Log.d("LOLSL", ratings.toString())
                                        val dataI=DataSeller(namaS,passS,ratings,stocks,hargas,transaksis, stockOrder,owner.toString())
                                        db.collection("Seller").document(text3.toString()).set(dataI)
                                        val intents = Intent(this@Ratingpage, Home::class.java)
                                        startActivity(intents)
                                    }
                                }
                            }
                    }
            }
        }
    }
}