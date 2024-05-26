package cl.td.suboch.alkewallet.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import cl.td.suboch.alkewallet.R
import cl.td.suboch.alkewallet.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val crearCuenta = findViewById<Button>(R.id.button_crear_cuenta)
        crearCuenta.setOnClickListener {
            val abrirPantallaCrear = Intent(this, SignupActivity::class.java)
            //abrirPantallaLogin.putExtra("nombre", "Alex")
            // abrirPantallaLogin.putExtra("apellido", "Suboch")
            // abrirPantallaLogin.putExtra("acepto_tyC", false)
            startActivity(abrirPantallaCrear)
        }

        val fullTransfer = findViewById<Button>(R.id.button_login)
        fullTransfer.setOnClickListener {
            val abrirPantallaTransferFull = Intent(this, HomePageActivity::class.java)
            //abrirPantallaLogin.putExtra("nombre", "Alex")
            // abrirPantallaLogin.putExtra("apellido", "Suboch")
            // abrirPantallaLogin.putExtra("acepto_tyC", false)
            startActivity(abrirPantallaTransferFull)
        }

        binding.buttonLogin.setOnClickListener {
            //val intent = Intent(this, ListActivity::class.java)
            //val intent = Intent(this, TabbedActivity::class.java)
            val intent = Intent(this, RecyclerViewActivity::class.java)
            startActivity(intent)
        }

    }
}