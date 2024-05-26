package cl.td.suboch.alkewallet.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cl.td.suboch.alkewallet.R
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
        val recyclerView: RecyclerView = findViewById(R.id.lista_recycler_home)
        //val miInstancia = Singleton.obtenerInstancia()
        //miInstancia.datoEjemplo = "Hola, mundo!"

//crear lista de peliculas
        val transactions = arrayOf(
            "Yara Khalil",
            "Sara Ibrahim",
            "Ahmad Ibrahim",
            "Reem Khaled",
            "Hiba Saleh"
        )

        // Set up the RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TransactionAdapter(transactions)
        //Configuramos el adaptador que se va a encagar de unir los datos con la vista
       // val adaptadorTransaction = TransactionAdapter(transactions.sortedArray())
      //  binding.listaRecycler.adapter = adaptadorTransaction

    }


}
