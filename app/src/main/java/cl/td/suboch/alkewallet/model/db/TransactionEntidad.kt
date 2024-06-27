package cl.td.suboch.alkewallet.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")

data class TransactionEntidad(

    //los campos para guardar en la base de datos
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val id_api: Int,
    val date: String,
    val amount: String,
    val type: String,
    val concept: String
)
