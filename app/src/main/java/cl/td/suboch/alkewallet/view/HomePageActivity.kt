package cl.td.suboch.alkewallet.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import cl.td.suboch.alkewallet.AlkeWalletApp
import cl.td.suboch.alkewallet.AlkeWalletApp.Companion.usuarioLogeado
import cl.td.suboch.alkewallet.R
import cl.td.suboch.alkewallet.adapter.TransactionAdapter
import cl.td.suboch.alkewallet.databinding.ActivityHomePageBinding
import cl.td.suboch.alkewallet.model.Transaction

class HomePageActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomePageBinding

    private lateinit var listTransactions: Array<String>
    private lateinit var transactionAdapter: TransactionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set OnClickListener using View Binding
        binding.receiveMoney.setOnClickListener {
            // Handle button click
            // For example: show a toast
            Toast.makeText(this, "Button clicked!", Toast.LENGTH_SHORT).show()
        }
/*
        val ingreso = findViewById<Button>(R.id.receive_money)
        ingreso.setOnClickListener {
            val ingresarDinero = Intent(this, RequestMoneyActivity::class.java)
            startActivity(ingresarDinero)
        }

 */
        //val envio = findViewById<Button>(R.id.send_money)
        binding.sendMoney.setOnClickListener {
            val enviarDinero = Intent(this, SendMoneyActivity::class.java)
            startActivity(enviarDinero)
        }
        //val perfil = findViewById<ImageView>(R.id.perfilIcon)
        binding.perfilIcon.setOnClickListener {
            val abrirPerfil = Intent(this, ProfileActivity::class.java)
            startActivity(abrirPerfil)
        }
    }

}