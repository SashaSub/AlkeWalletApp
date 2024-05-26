package cl.td.suboch.alkewallet.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cl.td.suboch.alkewallet.databinding.ActivitySplashScreenBinding
import java.util.Timer
import java.util.TimerTask

class SplashScreenActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
       // binding.logo.setOnClick

        //abrir pantalla con un click
        //se declara la imagen como una variable
        /**
         *
        val logoApp = findViewById<ImageView>(R.id.logo)
        logoApp.setOnClickListener {
            val abrirPantallaLogin = Intent(this, LoginSignupActivity::class.java)
            abrirPantallaLogin.putExtra("nombre", "Alex")
            abrirPantallaLogin.putExtra("apellido", "Suboch")
            abrirPantallaLogin.putExtra("acepto_tyC", false)
            startActivity(abrirPantallaLogin)
        }
         */

          //Abrir pantalla con un timer
        var task: TimerTask? = object : TimerTask(){
            override fun run() {
                val abrirPantallaLogin = Intent(baseContext, LoginSignupActivity ::class.java)
                startActivity(abrirPantallaLogin)
            }
        }
        val timer = Timer()
        timer.schedule(task, 2000)
    }
}