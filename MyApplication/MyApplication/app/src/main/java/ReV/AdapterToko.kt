package ReV

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.OrderPage
import com.example.myapplication.R

class AdapterToko (
    private val listresult :ArrayList<DataToko>
        )  :RecyclerView.Adapter<AdapterToko.ListViewHolder>()

{


inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    var V : View =itemView
    var namatoko : TextView =itemView.findViewById(R.id.NamaToko)
    var hargabarang :TextView =itemView.findViewById(R.id.HargaBarang)
    var stockbarang: TextView  = itemView.findViewById(R.id.StockBarang)
    var FotoToko : ImageView= itemView.findViewById(R.id.imageView)
    var rating : TextView=itemView.findViewById(R.id.HargaBarangs)
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
        holder.namatoko.text = toko.Nama.toString()
        holder.hargabarang.text = toko.Harga.toString()
        holder.stockbarang.text = toko.Stock.toString()
//        holder.FotoToko.setImageResource(toko.image.toString())
        holder.rating.text = toko.Rating.toString()
        holder.order.setOnClickListener {

            val a = Intent(holder.V.context,OrderPage::class.java)
            a.putExtra("namatoko",toko.Nama)
            holder.V.context.startActivity(a)

        }
    }



    override fun getItemCount(): Int {
        return listresult.size
    }


}