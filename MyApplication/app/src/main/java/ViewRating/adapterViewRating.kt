package ViewRating

import Transaksi.adapterCompleteTransaksi
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.Rating
import com.example.myapplication.transaksiid
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class adapterViewRating(
    private val listresult : ArrayList<Rating>
)  : RecyclerView.Adapter<adapterViewRating.ListViewHolder>() {
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var V: View = itemView
        var namaPembeli: TextView = itemView.findViewById(R.id.namaPereview)
        var komentar: TextView = itemView.findViewById(R.id.koment)
        var viewRatings: TextView = itemView.findViewById(R.id.viewRatingRate)
        var FotoToko: ImageView = itemView.findViewById(R.id.imageView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewratinglayout, parent, false)
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
        holder.namaPembeli.text = transaksi.Rater.toString()
        holder.komentar.text = transaksi.Komentar.toString()
        holder.viewRatings.text = transaksi.Value.toString()

    }
}