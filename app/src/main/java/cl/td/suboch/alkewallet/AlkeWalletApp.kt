package cl.td.suboch.alkewallet

import android.app.Application
import androidx.room.Room
import cl.td.suboch.alkewallet.model.CuentaResponse
import cl.td.suboch.alkewallet.model.User
import cl.td.suboch.alkewallet.model.db.AppDb

class AlkeWalletApp : Application() {

    companion object{
        //crear un objeto Usuario global para el proyecto
        var usuarioLogeado : User? = null
        //crear un String que sera el Token
        var tokenAccesso : String? = ""
        // agregar la cuenta del usuario
        var cuentaUsuario: CuentaResponse? = null

        //Base de datos
        lateinit var database: AppDb
    }
    override fun onCreate() {
        super.onCreate()
        usuarioLogeado = null
        tokenAccesso = null

        // Configurar la base de datos Room
        database = Room.databaseBuilder(
            applicationContext,
            AppDb::class.java, "bd_transactions"
        ).build()
    }
}