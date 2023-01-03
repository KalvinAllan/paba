package com.example.myapplication

import Transaksi.adapterTransaksi
import ViewRating.adapterViewRating
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ViewRating : AppCompatActivity() {
    private lateinit var Fototoko: Array<Int>
    lateinit var  Recv : RecyclerView
    private var artoko= arrayListOf<Rating>()

    private fun SiapkanData(Penjual:String){
        val db = Firebase.firestore

        db.collection("Rating").document(Penjual)
            .get()
            .addOnSuccessListener{result ->
                var rater = result.data?.get("rater") as ArrayList<String>
                var koment = result.data?.get("komentar") as ArrayList<String>
                var value = result.data?.get("value") as ArrayList<String>
                var count = 0

                for(komentar in result.data?.get("komentar") as ArrayList<String>){
                    var data = Rating(result.data?.get("rater") as ArrayList<String>,result.data?.get("value") as ArrayList<Long>, result.data?.get("komentar") as ArrayList<String>)
                    artoko.add(data)
                    Recv.layoutManager = LinearLayoutManager(this)
                    Recv.adapter= adapterViewRating(artoko)

                }

            }


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_rating)
        Recv=findViewById<RecyclerView>(R.id.RV5)
        val text3=intent.getStringExtra("namatoko")
        if(text3 != null){
            SiapkanData(text3)
        }
    }
}