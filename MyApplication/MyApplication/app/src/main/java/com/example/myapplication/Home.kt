package com.example.myapplication


import ReV.AdapterToko
import ReV.DataToko
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Home : AppCompatActivity() {
    private lateinit var namatoko: Array<String>
    private lateinit var  hargabarang: Array<Int>
    private lateinit var stockbarang: Array<Int>
    private lateinit var ratingtoko: Array<Double>
    private lateinit var Fototoko: Array<Int>
    lateinit var  Recv :RecyclerView
    private var artoko = arrayListOf<DataToko>()

    private fun SiapkanData(){
        val db = Firebase.firestore
        var sharedPref =  getSharedPreferences("LogIn", Context.MODE_PRIVATE)
        val text = sharedPref.getString("usernameP", "")
        var datanama = mutableListOf<String>()
        var datastock  = mutableListOf<Int>()
        var dataharga  = mutableListOf<Int>()
        var rating = mutableListOf<Double>()
        db.collection("Seller")
            .get()
            .addOnSuccessListener{result ->
                for (document in result){
                    Log.d("Feast",text.toString())
                    if (document.data.get("owner")!=text.toString()){
                        Log.d("TEsting",document.id.toString())
                        datanama.add(document.id)
//                    datastock.add(document.data.get("stock") as Int)
//                    dataharga.add(document.data.get("harga")as Int)
//                    rating.add(document.data.get("rating").toString().toDouble())
                        var data =DataToko(document.id, document.data.get("stock").toString().toInt(),
                            document.data.get("harga").toString().toInt(),0,document.data.get("rating").toString().toDouble())
                        artoko.add(data)
                        Log.d("nama", data.toString())
                    }
                }

                namatoko=datanama.toTypedArray()
                stockbarang=datastock.toTypedArray()
                hargabarang=dataharga.toTypedArray()
                ratingtoko=rating.toTypedArray()
//                Recv.adapter= AdapterToko(artoko)

                Log.d("nama", artoko.toString())
                Log.d("nama","100")
                Log.d("nama", artoko.toString())
                Recv.layoutManager =LinearLayoutManager(this)
                Recv.adapter= AdapterToko(artoko)
                artoko = arrayListOf<DataToko>()
                fun  pindah(pos:Int){
                    val intent = Intent(this@Home,OrderPage::class.java)
                    startActivity(intent)
                }
//                Recv.adapter?.notifyDataSetChanged()
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
//    private fun TampilkanData(){
//        Log.d("nama", artoko.toString())
//        Recv.layoutManager =LinearLayoutManager(this)
//        Recv.adapter= AdapterToko(artoko)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home2)
        val sharedPref =  getSharedPreferences("LogIn", Context.MODE_PRIVATE)
        val text = sharedPref.getString("usernameP", "")
        Log.d("Ne",text.toString())
        val buttonHistory = findViewById<ImageView>(R.id.HistoryButtonSSSS)

        buttonHistory.setOnClickListener{
            val intents = Intent(this@Home, Transaction::class.java)
            startActivity(intents)
        }

        val nama = findViewById<TextView>(R.id.NamaU)
        Recv=findViewById<RecyclerView>(R.id.RV)
        nama.text = text.toString()
        nama.setOnClickListener {
            val intents = Intent(this@Home, editprofile::class.java)
            startActivity(intents)
        }
        SiapkanData()
            }

//    override fun onPause() {
//        super.onPause()
//        super.onStop()
//        finish()
//    }

    override fun onResume() {
        super.onResume()
        Log.d("siap", "exe")
        SiapkanData()
    }
    }