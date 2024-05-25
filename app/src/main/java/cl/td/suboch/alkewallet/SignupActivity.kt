package cl.td.suboch.alkewallet

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cl.td.suboch.alkewallet.databinding.ActivityProfileBinding
import cl.td.suboch.alkewallet.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

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