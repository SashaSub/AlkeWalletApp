package cl.td.suboch.alkewallet

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val login = findViewById<Button>(R.id.button_logear)
        login.setOnClickListener {
            val abrirPantallaLogin = Intent(this, LoginActivity::class.java)
            startActivity(abrirPantallaLogin)
        }

        val emptyTransfer = findViewById<Button>(R.id.button_login)
        emptyTransfer.setOnClickListener {
            val homePageEmpty = Intent(this, HomePageEmptyActivity::class.java)
            startActivity(homePageEmpty)
        }
    }
}