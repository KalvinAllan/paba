package ReV

import android.content.ComponentCallbacks
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity
import com.example.myapplication.MainActivity2
import com.example.myapplication.R

class AdapterToko (
    private val listresult :ArrayList<DataToko>
        )  :RecyclerView.Adapter<AdapterToko.ListViewHolder>()
{
inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    var namatoko : TextView =itemView.findViewById(R.id.NamaToko)
    var hargabarang :TextView =itemView.findViewById(R.id.HargaBarang)
    var stockbarang: TextView  = itemView.findViewById(R.id.StockBarang)
    var FotoToko : ImageView= itemView.findViewById(R.id.imageView)
    var rating : TextView=itemView.findViewById(R.id.Rating)
    var order : Button=itemView.findViewById(R.id.Order)
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view:View =LayoutInflater.from(parent.context)
            .inflate(R.layout.isi,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var toko  =listresult[position]
        Log.d("Mana",listresult.toString())
        Log.d("Mana", position.toString())
        Log.d("Mana", holder.toString())
        holder.namatoko.setText(toko.Nama.toString())
        holder.hargabarang.setText(toko.Harga.toString())
        holder.stockbarang.setText(toko.Stock.toString())
//        holder.FotoToko.setImageResource(toko.image.toString())
        holder.rating.setText(toko.Rating.toString())
        holder.rating.setOnClickListener{
            val intents = Intent(this@AdapterToko, MainActivity::class.java)
            startActivity(intents)
        }
    } 




    override fun getItemCount(): Int {
        return listresult.size
    }


}