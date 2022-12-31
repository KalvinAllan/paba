package Transaksi

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.DataSeller
import com.example.myapplication.R
import com.example.myapplication.Transaction
import com.example.myapplication.transaksiid
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class adapterCompleteTransaksi (
    private val listresult : ArrayList<transaksiid>
)  : RecyclerView.Adapter<adapterCompleteTransaksi.ListViewHolder>() {
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var V: View = itemView
        var namaPembeli: TextView = itemView.findViewById(R.id.NameTransaksiSeller)
        var hargabarang: TextView = itemView.findViewById(R.id.HargaBarangs)
        var jumlahbarang: TextView = itemView.findViewById(R.id.JumlahBarangTransaksiSeller)
        var FotoToko: ImageView = itemView.findViewById(R.id.imageView)
        var CompleteOrder: Button = itemView.findViewById(R.id.FinishOrder)
        var status: TextView = itemView.findViewById(R.id.isCompleteOrderSeller)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.transeller, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listresult.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val db = Firebase.firestore

        var transaksi = listresult[position]
        Log.d("Mana", listresult.toString())
        Log.d("Mana", position.toString())
        Log.d("Mana", holder.toString())
        holder.namaPembeli.text = transaksi.namaP.toString()
        holder.jumlahbarang.text = transaksi.jumlahbarang.toString()
        holder.hargabarang.text = transaksi.harga.toString()
        if (transaksi.status == 1) {
            holder.status.text = "Completed"
        } else {
            holder.status.text = "Ongoing"
        }
//        holder.id.text=transaksi.id.toString()
        if (transaksi.status == 1){
            holder.CompleteOrder.isEnabled = false
        }
        holder.CompleteOrder.setOnClickListener {
//            val a = Intent(holder.V.context, Transaction::class.java)
//            a.putExtra("namatoko",transaksi.nama)
//            holder.V.context.startActivity(a)
            val a = db.collection("transaksiid").document(transaksi.id.toString()).get()
                .addOnSuccessListener { result ->
//                    val jumlahbarang = result.data?.get("jumlahbarang").toString().toInt()

                    db.collection("Seller").document(result.data?.get("nama").toString())
                        .get()
                        .addOnSuccessListener {resSeller->
                            var CurStockOrder = resSeller.data?.get("stockOrder").toString().toInt() - result.data?.get("jumlahbarang").toString().toInt()
                            var datas = DataSeller(
                                resSeller.data?.get("nama").toString(),
                                resSeller.data?.get("pass").toString(),
                                resSeller.data?.get("rating").toString().toDouble(),
                                resSeller.data?.get("stock").toString().toInt(),
                                resSeller.data?.get("harga").toString().toInt(),
                                resSeller.data?.get("transaksi") as ArrayList<Long>,
                                CurStockOrder)
                            db.collection("Seller").document(resSeller.id).set(datas)
                            val data = transaksiid(
                                result.id.toInt(),
                                result.data?.get("namaP").toString(),
                                result.data?.get("nama").toString(),
                                result.data?.get("harga").toString().toInt(),
                                result.data?.get("jumlahbarang").toString().toInt(),
                                1
                            )
                            db.collection("transaksiid").document(transaksi.id.toString()).set(data)

                        }
                    holder.status.text = "Completed"
                    holder.CompleteOrder.isEnabled = false

                }
        }
    }
}