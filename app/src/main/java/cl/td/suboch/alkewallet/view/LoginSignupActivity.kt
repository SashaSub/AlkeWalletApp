package cl.td.suboch.alkewallet.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import cl.td.suboch.alkewallet.R
import cl.td.suboch.alkewallet.databinding.ActivityLoginSignupBinding

class LoginSignupActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginSignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginSignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLogear.setOnClickListener {
            val abrirPantallaLogin = Intent(this, LoginActivity::class.java)
            startActivity(abrirPantallaLogin)
        }

        binding.buttonCrearCuenta.setOnClickListener {
            val abrirPantallaCrear = Intent(this, SignupActivity::class.java)
            startActivity(abrirPantallaCrear)
        }
    }

}