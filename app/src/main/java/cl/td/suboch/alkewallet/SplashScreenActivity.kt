package cl.td.suboch.alkewallet

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Timer
import java.util.TimerTask

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)
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
        val logoApp = findViewById<ImageView>(R.id.logo)
                  var task: TimerTask? = object : TimerTask(){
                      override fun run() {
                          val abrirPantallaLogin = Intent(baseContext, LoginSignupActivity ::class.java)
                          abrirPantallaLogin.putExtra("nombre", "Alex")
                          abrirPantallaLogin.putExtra("apellido", "Suboch")
                          abrirPantallaLogin.putExtra("acepto_tyC", false)
                          startActivity(abrirPantallaLogin)
                      }
                  }
                  val timer = Timer()
                  timer.schedule(task, 2000)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}