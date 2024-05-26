package cl.td.suboch.alkewallet.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import cl.td.suboch.alkewallet.R
import cl.td.suboch.alkewallet.model.Transaction

class TransactionAdapter(private val listTransactions: Array<String>)
    : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>(){
    class TransactionViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {
        val userName: TextView
        val dateTransaction: TextView
        val amountTransaction: TextView
        val avatarTransaction: ImageView

        init {
            //Define click listener for the ViewHolder's View
            userName = itemView.findViewById(R.id.user_name)
            dateTransaction = itemView.findViewById(R.id.date_transaction)
            amountTransaction = itemView.findViewById(R.id.amount_transaction)
            avatarTransaction = itemView.findViewById(R.id.avatar_transaction)
        }
    }

    //Create new views. Enlaza el archivo xml con la clase ViewHolder que generamos arriba
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transaction, parent, false)
        return TransactionViewHolder(view)
    }

    //Esta funcion devuelve la cantidad de items que va a mostrar el Recycler
    override fun getItemCount(): Int {
        return listTransactions.size
    }

    //Configurar información en cada celda que vamos a mostrar en la lista
    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        //Get element from your dataset at this position and replace the contents of the view with that element
        //mostrar el titulo de la pelicula
        //val currentTransaction = listTransactions[position]
        //holder.userName.text = listTransactions[position]
        holder.userName.text = listTransactions[position]
        //agregar texto en subtitulo
        holder.dateTransaction.text = "Oct 14, 10:24 AM"
        holder.amountTransaction.text = "$15.00"
    }


}