package cl.td.suboch.alkewallet

import android.app.Application
import androidx.room.Room
import cl.td.suboch.alkewallet.model.db.AppDb

class AppRoom : Application() {
    companion object {
        //Base de datos
        lateinit var database: AppDb
    }
    override fun onCreate() {
        super.onCreate()
        // Configurar la base de datos Room
        database = Room.databaseBuilder(
            applicationContext,
            AppDb::class.java, "bd_transactions"
        ).build()
    }
}