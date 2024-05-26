package cl.td.suboch.alkewallet.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cl.td.suboch.alkewallet.adapter.TransactionAdapter
import cl.td.suboch.alkewallet.databinding.ActivityHomePageBinding
import cl.td.suboch.alkewallet.databinding.ActivityRecyclerViewBinding

class RecyclerViewActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomePageBinding
            //ActivityRecyclerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
            //ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val miInstancia = Singleton.obtenerInstancia()
        //miInstancia.datoEjemplo = "Hola, mundo!"

//crear lista de peliculas
        val transactions = arrayOf(
            "Ghost",
            "El silencio de los inocentes",
            "La edad de oro",
            "Los muertos luchan por vivir",
            "El padrino",
            "Top Gun",
            "Avatar",
            "El mundo de Wain",
            "Naranja mecánica",
            "Misión imposible",
            "Solaris",
            "El guardaespalda",
            "Titanic",
            "Incendio bajo del mar",
            "La esquina en la casa redonda"
        )

        //Configuramos el adaptador que se va a encagar de unir los datos con la vista
        val adaptadorTransaction = TransactionAdapter(transactions.sortedArray())
        binding.listaTransactionHome.adapter = adaptadorTransaction

    }


}
