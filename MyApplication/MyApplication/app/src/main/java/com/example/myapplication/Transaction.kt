package com.example.myapplication

import ReV.AdapterToko
import ReV.DataToko
import Transaksi.adapterTransaksi
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class Transaction : AppCompatActivity() {
    private lateinit var Fototoko: Array<Int>
    lateinit var  Recv : RecyclerView
    private var artoko= arrayListOf<transaksiid>()

    private fun SiapkanData(){
        val db = Firebase.firestore
        val sharedPref =  getSharedPreferences("LogIn", Context.MODE_PRIVATE)
        val text = sharedPref.getString("usernameP", "")
        if (text != null) {
            db.collection("User").document(text)
                .get()
                .addOnSuccessListener{result ->
                    for(ids in result.data?.get("transaksiid") as ArrayList<Long>){
                        db.collection("transaksiid").document(ids.toString())
                            .get()
                            .addOnSuccessListener {result2 ->
                                var data = transaksiid(result2.id.toInt(), result2.data?.get("namaP").toString(), result2.data?.get("nama").toString(), result2.data?.get("harga").toString().toInt(),result2.data?.get("jumlahbarang").toString().toInt(), result2.data?.get("status").toString().toInt())
                                artoko.add(data)
                                Log.d("nama", artoko.toString())
                                Log.d("nama","100")
                                Log.d("nama", artoko.toString())
                                Recv.layoutManager = LinearLayoutManager(this)
                                Recv.adapter= adapterTransaksi(artoko)
                                //                Recv.adapter?.notifyDataSetChanged()
                            }
                    }
    //                Recv.adapter= AdapterToko(artoko)


                }
        }


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)
        Recv=findViewById<RecyclerView>(R.id.RV2)
        SiapkanData()


    }
}