package cl.td.suboch.alkewallet

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginSignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_signup)

        /**
         *  //rescatando los valores de la pantalla anterior
         *         val nombre = intent.getStringExtra("nombre")
         *         val apellido = intent.getStringExtra("apellido")
         *         val tyc = intent.getBooleanExtra("acepto_tyC", false)
         *         //mostrar el dato en un toast
         *         Toast.makeText(this, "Hola $nombre $apellido -- Acepto los tyc $tyc", Toast.LENGTH_SHORT).show()
         *
         *         ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
         *             val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
         *             v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
         *             insets
         *         }
         *     }
         */

        val login = findViewById<Button>(R.id.button_logear)
        login.setOnClickListener {
            val abrirPantallaLogin = Intent(this, LoginActivity::class.java)
            //abrirPantallaLogin.putExtra("nombre", "Alex")
            // abrirPantallaLogin.putExtra("apellido", "Suboch")
            // abrirPantallaLogin.putExtra("acepto_tyC", false)
            startActivity(abrirPantallaLogin)
        }

        val crearCuenta = findViewById<Button>(R.id.button_crear_cuenta)
        crearCuenta.setOnClickListener {
            val abrirPantallaCrear = Intent(this, SignupActivity::class.java)
            //abrirPantallaLogin.putExtra("nombre", "Alex")
            // abrirPantallaLogin.putExtra("apellido", "Suboch")
            // abrirPantallaLogin.putExtra("acepto_tyC", false)
            startActivity(abrirPantallaCrear)
        }
    }

}