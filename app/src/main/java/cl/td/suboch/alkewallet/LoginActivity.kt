package cl.td.suboch.alkewallet

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

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
    }
}