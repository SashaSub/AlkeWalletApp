package cl.td.suboch.alkewallet.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TransactionsDao {

    //metodo para insertar toda la data
    @Insert
    fun insertarData(transactions : List<TransactionEntidad>)

    //metodos para traer esta informacion
    @Query("Select * from transactions")
    fun obtenerTransactionsDB() : List<TransactionEntidad>

    //metodo para borrar toda la data
    @Query("DELETE FROM transactions")
    fun borrarDB()
}