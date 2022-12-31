package com.example.myapplication

import Transaksi.adapterCompleteTransaksi
import Transaksi.adapterTransaksi
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CompleteTransaction : AppCompatActivity() {
    private lateinit var Fototoko: Array<Int>
    lateinit var  Recv : RecyclerView
    private var artoko= arrayListOf<transaksiid>()

    private fun SiapkanData(){
        val db = Firebase.firestore
        val sharedPref =  getSharedPreferences("LoginPenjual", Context.MODE_PRIVATE)
        val text = sharedPref.getString("username", "")
        if (text != null) {
            Log.d("Bug", text)
            db.collection("Seller").document(text)
                .get()
                .addOnSuccessListener{result ->
                    var resultArrays = result.data?.get("transaksi") as ArrayList<Long>
                    Log.d("Bug", resultArrays.toString())
                    for(ids in resultArrays){
                        db.collection("transaksiid").document(ids.toString())
                            .get()
                            .addOnSuccessListener {result2 ->
                                Log.d("Testingss", result2.toString())
                                Log.d("Testingss", result2.data?.get("harga").toString())
                                var data = transaksiid(result2.id.toInt(),
                                    result2.data?.get("namaP").toString(),
                                    result2.data?.get("nama").toString(),
                                    result2.data?.get("harga").toString().toInt(),
                                    result2.data?.get("jumlahbarang").toString().toInt(),
                                    result2.data?.get("status").toString().toInt())
                                artoko.add(data)
                                Log.d("nama", artoko.toString())
                                Log.d("nama","100")
                                Log.d("nama", artoko.toString())
                                Recv.layoutManager = LinearLayoutManager(this)
                                Recv.adapter= adapterCompleteTransaksi(artoko)
                                //                Recv.adapter?.notifyDataSetChanged()
                            }
                    }

                    //                Recv.adapter= AdapterToko(artoko)


                }
        }


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_transaction)
        Recv=findViewById<RecyclerView>(R.id.RV3)
        SiapkanData()
    }
}