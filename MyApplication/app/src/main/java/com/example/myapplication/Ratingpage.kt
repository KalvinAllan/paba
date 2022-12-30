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
        var namaS=""
        var passS=""
        var ratings=0.0
        var stocks=0
        var hargas=0
        var Rater= ArrayList<String>()
        var komentar=ArrayList<String>()
        var value =ArrayList<Int>()
        var transaksis=ArrayList<Long>()
        val db = Firebase.firestore
        val rater=ArrayList<String>()
        val star=ArrayList<Int>()
        val komen=ArrayList<String>()
        val text3="e"
        var tampung=ArrayList<Double>()
        var count=0
        var score =0.0

        update.setOnClickListener {
            rater.add(text.toString())
            star.add(rating.text.toString().toInt())
            komen.add(Komentar.text.toString())
            Log.d("haha", rater.toString())
            Log.d("haha", star.toString())
            Log.d("haha", komen.toString())
            val dataInput = Rating(rater,star,komen)
            Log.d("MIC","Masuk1")
            db.collection("Rating").document(text3.toString()).set(dataInput)
//            val retirvedata =db.collection("Seller")
//                .get()
//                .addOnSuccessListener { result->
//                    Log.d("MIC","Masuk2")
//                for (document in result){
//                    if (text3.toString()==document.data.get("nama")){
//                        Log.d("MIC","Masuk3")
//                        namaS=document.data.get("nama").toString()
//                        passS=document.data.get("pass").toString()
//                        hargas=document.data.get("nama").toString().toInt()
//                        ratings=document.data.get("pass").toString().toDouble()
//                        stocks=document.data.get("stock").toString().toInt()
//                        transaksis= document.data.get("transaksi") as ArrayList<Long>
//                        val retrat =db.collection("Rating")
//                            .get()
//                            .addOnSuccessListener{ result->
//                                for(document in result){
//                                    Log.d("MIC","Masuk4")
//                                    if (namaS == document.id){
//                                        Log.d("MIC","Masuk5")
//
//                                        tampung= document.data.get("Value") as ArrayList<Double>
//                                        Log.d("MIC",tampung.toString())
//                                    }
//                                }
//
//                                count=tampung.size
//                                while (count>=0){
//                                    Log.d("MIC","Masuk6")
//                                    score= score+tampung[count]
//                                    count=count-1
//                                }
//                                Log.d("MIC","Masuk7")
//                                ratings=score/tampung.size
//                                Log.d("MIC","Masuk8")
//                                Log.d("MIC",ratings.toString())
//                                val dataI =DataSeller(namaS,passS,ratings,stocks,hargas,transaksis)
//                                db.collection("Seller").document().set(dataI)
//                            }
//
//                    }
//                }

//                }
//            val intents = Intent(this@Ratingpage, MainActivity::class.java)
//            startActivity(intents)
        }
    }
}