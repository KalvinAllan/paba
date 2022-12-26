package ReV

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view:View =LayoutInflater.from(parent.context)
            .inflate(R.layout.isi,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//        super.onBindViewHolder(holder,0)
//        var toko  =listresult[position]
//        holder.namatoko.setText(toko.Nama)
//        holder.hargabarang.setText(toko.Harga)
//        holder.stockbarang.setText(toko.Stock)
//        holder.FotoToko.setImageResource(toko.image)
    }

    override fun getItemCount(): Int {
        return listresult.size
    }


}