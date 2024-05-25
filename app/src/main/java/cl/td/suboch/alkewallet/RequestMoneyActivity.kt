package cl.td.suboch.alkewallet

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cl.td.suboch.alkewallet.databinding.ActivityProfileBinding
import cl.td.suboch.alkewallet.databinding.ActivityRequestMoneyBinding

class RequestMoneyActivity : AppCompatActivity() {

    lateinit var binding: ActivityRequestMoneyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequestMoneyBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}