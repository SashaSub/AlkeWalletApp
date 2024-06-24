package cl.td.suboch.alkewallet

import android.app.Application
import cl.td.suboch.alkewallet.model.User

class AlkeWalletApp : Application() {

    companion object{
        //crear un objeto Usuario global para el proyecto
        var usuarioLogeado : User? = null
        //crear un String que sera el Token
        var tokenAccesso : String? = ""
    }

    override fun onCreate() {
        super.onCreate()
        usuarioLogeado = null
        tokenAccesso = null
    }
}