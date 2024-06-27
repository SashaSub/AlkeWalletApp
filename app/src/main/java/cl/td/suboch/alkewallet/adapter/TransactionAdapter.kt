package cl.td.suboch.alkewallet.adapter

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import cl.td.suboch.alkewallet.AlkeWalletApp
import cl.td.suboch.alkewallet.R
import cl.td.suboch.alkewallet.model.Transaction
import com.squareup.picasso.Picasso
import java.util.Date
import java.util.Locale

class TransactionAdapter(private val listTransactions: List<Transaction>)
    : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>(){
    class TransactionViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {

     //   val shortDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).format(Date())

        val type: TextView
        val concept: TextView
        val dateTransaction: TextView
        val amountTransaction: TextView
        val avatarTransaction: ImageView
        init {
            //Define click listener for the ViewHolder's View
            type = itemView.findViewById(R.id.transaction_type)
            concept = itemView.findViewById(R.id.transaction_note)
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
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        //Get element from your dataset at this position and replace the contents of the view with that element
        val currentTransaction = listTransactions[position]
        //holder.userName.text = AlkeWalletApp.usuarioLogeado?.first_name + ' ' + AlkeWalletApp.usuarioLogeado?.last_name
        //agregar info de transacciones
        holder.type.text = currentTransaction.type
        holder.concept.text = currentTransaction.concept
        holder.dateTransaction.text = currentTransaction.date
        holder.amountTransaction.text = "$${currentTransaction.amount}"
        holder.avatarTransaction.setImageResource(R.drawable.baseline_person_24) // Utiliza una imagen por defecto
    }


}