package cl.td.suboch.alkewallet.model.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TransactionEntidad::class], version = 1)
abstract class AppDb : RoomDatabase() {
    abstract fun transactionDao() : TransactionsDao
}