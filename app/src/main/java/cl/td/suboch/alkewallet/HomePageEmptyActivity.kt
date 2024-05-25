package cl.td.suboch.alkewallet

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cl.td.suboch.alkewallet.databinding.ActivityHomePageBinding
import cl.td.suboch.alkewallet.databinding.ActivityHomePageEmptyBinding

class HomePageEmptyActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomePageEmptyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageEmptyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ingreso = findViewById<Button>(R.id.receive_money)
        ingreso.setOnClickListener {
            val ingresarDinero = Intent(this, RequestMoneyActivity::class.java)
            startActivity(ingresarDinero)
        }
        val envio = findViewById<Button>(R.id.send_money)
        envio.setOnClickListener {
            val enviarDinero = Intent(this, SendMoneyActivity::class.java)
            startActivity(enviarDinero)
        }
        val perfil = findViewById<ImageView>(R.id.perfilIcon)
        perfil.setOnClickListener {
            val abrirPerfil = Intent(this, ProfileActivity::class.java)
            startActivity(abrirPerfil)
        }
    }
}