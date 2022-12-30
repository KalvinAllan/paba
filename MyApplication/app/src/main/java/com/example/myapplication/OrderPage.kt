package com.example.myapplication

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

class OrderPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_page)
        val nama = findViewById<TextView>(R.id.NamaTokoOP)
        val rating = findViewById<TextView>(R.id.RatingOP)
        val harga = findViewById<TextView>(R.id.HargaOP)
        val stock = findViewById<TextView>(R.id.stockOP)
        val barang = findViewById<TextView>(R.id.JumlahPesan)
        val Next = findViewById<TextView>(R.id.OrderOP)
        val db = Firebase.firestore
        var TransaksiPenjual= ArrayList<Long>()
        val dataintent=intent.getStringExtra("namatoko")
        var  pass=""
        var stockN=0
        var namaT=""
        var ratingT  =0.0
        var hargaT =0
        var StockO=0
        var hargatotal=0
        val sharedPref =  getSharedPreferences("LogIn", Context.MODE_PRIVATE)
        val text = sharedPref.getString("usernameP", "")

        if (dataintent!=null){
            val retrieveData = db.collection("Seller")
                .get()
                .addOnSuccessListener { result->
                    Log.d("test","Masuk")
                    for (document in result){
                        Log.d("test","Masuk2")
                        Log.d("test",dataintent.toString())
                        if (dataintent.toString() ==document.data.get("nama").toString()){
                            Log.d("test","Masuk3")
                            pass=document.data.get("pass").toString()
                            namaT=document.data.get("nama").toString()
                            hargaT=document.data.get("harga").toString().toInt()
                            ratingT=document.data.get("rating").toString().toDouble()
                            StockO=document.data.get("stock").toString().toInt()
                            TransaksiPenjual= document.data.get("transaksi") as ArrayList<Long>
                            Log.d("self", TransaksiPenjual.toTypedArray().toString())
                            Log.d("test",pass)
                            Log.d("test",namaT)
                            Log.d("test", hargaT.toString())
                            Log.d("test",ratingT.toString())
                            Log.d("test",StockO.toString())
                            nama.setText(namaT)
                            harga.setText(hargaT.toString())
                            stock.setText(StockO.toString())
                            rating.setText(ratingT.toString())
                        }
                    }
                }
            Next.setOnClickListener {
                var minus=barang.text.toString().toInt()
                Log.d("test","Masuk4")
                Log.d("test",pass)
                Log.d("test",namaT)
                Log.d("test", hargaT.toString())
                Log.d("test",ratingT.toString())
                Log.d("test",StockO.toString())
                Log.d("test",minus.toString())
                stockN=StockO-minus
                Log.d("test",stockN.toString())
                if (stockN < 0|| minus<=0){
                    Toast.makeText(this@OrderPage,"Jumlah order terlalu banyak / terlalu sedikit",Toast.LENGTH_LONG).show()
                }
                else if (stockN  >=0){
                    
                    val order = db.collection("transaksiid")
                        .get()
                        .addOnSuccessListener {result->
                            var count=1
                            for (document  in result){
                                count+=1
                            }
                            TransaksiPenjual.add(count.toLong())
                         val dataInput = DataSeller(namaT,pass,ratingT,stockN,hargaT,TransaksiPenjual)
                         db.collection("Seller").document(namaT).set(dataInput)
                            val data =transaksiid(count,text.toString(),namaT,hargaT,minus,0)
                            db.collection("transaksiid")
                                .document(count.toString()).set(data)
                            val intents = Intent(this@OrderPage, Transaction::class.java)
                            startActivity(intents)
                        }

                }

            }
        }



    }


}


