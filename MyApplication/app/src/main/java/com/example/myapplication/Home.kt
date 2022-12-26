package com.example.myapplication


import ReV.AdapterToko
import ReV.DataToko
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Home : AppCompatActivity() {
    private lateinit var namatoko: Array<String>
    private lateinit var  hargabarang: Array<Int>
    private lateinit var stockbarang: Array<Int>
    private lateinit var Fototoko: Array<Int>
    lateinit var  Recv :RecyclerView
    private var artoko= arrayListOf<DataToko>()

    private fun SiapkanData(){
        val db = Firebase.firestore

        var datanama = mutableListOf<String>()
        var datastock  = mutableListOf<Int>()
        var dataharga  = mutableListOf<Int>()
        db.collection("Seller")
            .get()
            .addOnSuccessListener{result ->
                for (document in result){
                    datanama.add(document.id)
                    Log.d("nama","10")
//                    datastock.add(document.data.get("stock") as Int)
//                    dataharga.add(document.data.get("harga")as Int)
                    var data =DataToko(document.id, "3",
                        "30000",0)
                    artoko.add(data)
                }

                namatoko=datanama.toTypedArray()
                stockbarang=datastock.toTypedArray()
                hargabarang=dataharga.toTypedArray()
//                Recv.adapter= AdapterToko(artoko)
                Recv.adapter?.notifyDataSetChanged()
                Log.d("nama","100")
            }


    }
//    private fun tambahdata(){
//        for (position in namatoko.indices){
//            val data = DataToko(
//                namatoko[position],
//                stockbarang[position],
//                hargabarang[position],0
////                Fototoko[0]
//            )
//            artoko.add(data)
//        }
//    }
    private fun TampilkanData(){

        Recv.layoutManager =LinearLayoutManager(this)
        Recv.adapter= AdapterToko(artoko)
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home2)
        Recv=findViewById<RecyclerView>(R.id.RV)
        var data = mutableListOf<String>()
        val db = Firebase.firestore
        db.collection("Seller")
            .get()
            .addOnSuccessListener{result ->
                for (document in result){
                    data.add(document.id)
                }
                SiapkanData()
                TampilkanData()

            }



    }
}