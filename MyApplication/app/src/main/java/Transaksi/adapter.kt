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
import com.example.myapplication.R
import com.example.myapplication.Transaction
import com.example.myapplication.transaksiid

class adapter (
    private val listresult : ArrayList<transaksiid>
)  : RecyclerView.Adapter<adapter.ListViewHolder>() {
    inner class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var V : View =itemView
        var namatoko : TextView =itemView.findViewById(R.id.NamaTokoT)
        var hargabarang : TextView =itemView.findViewById(R.id.Harga)
        var jumlahbarang: TextView = itemView.findViewById(R.id.Jumlah)
        var FotoToko : ImageView = itemView.findViewById(R.id.imageView)
        var id : TextView =itemView.findViewById(R.id.id)
        var Rateorder: Button =itemView.findViewById(R.id.OrderT)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view:View = LayoutInflater.from(parent.context)
            .inflate(R.layout.isistransaksi,parent,false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listresult.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var transaksi  =listresult[position]
        Log.d("Mana", listresult.toString())
        Log.d("Mana", position.toString())
        Log.d("Mana", holder.toString())
        holder.namatoko.text =transaksi.nama.toString()
        holder.jumlahbarang.text=transaksi.jumlahbarang.toString()
        holder.hargabarang.text=transaksi.harga.toString()
        holder.id.text=transaksi.id.toString()

        holder.Rateorder.setOnClickListener {
            val a = Intent(holder.V.context, Transaction::class.java)
            a.putExtra("namatoko",transaksi.nama)
            holder.V.context.startActivity(a)

        }
    }
}