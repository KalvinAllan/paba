package com.example.myapplication

data class DataSeller(
    var nama: String,
    var pass: String,
    var rating :Double,
    var stock: Int,
    var harga:Int,
    var Transaksi:ArrayList<Long>,
    var stockOrder: Int

)
