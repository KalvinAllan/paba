package com.example.myapplication

import ReV.DataToko
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

private lateinit var namatoko: Array<String>
private lateinit var  hargabarang: Array<Int>
private lateinit var stockbarang: Array<Int>
private lateinit var ratingtoko: Array<Double>
private lateinit var Fototoko: Array<Int>
lateinit var  Recv : RecyclerView
private var artoko= arrayListOf<DataToko>()

class Transaction : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

    }
}