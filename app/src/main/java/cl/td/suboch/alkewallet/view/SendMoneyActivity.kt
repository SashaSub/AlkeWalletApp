package cl.td.suboch.alkewallet.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cl.td.suboch.alkewallet.databinding.ActivityProfileBinding
import cl.td.suboch.alkewallet.databinding.ActivitySendMoneyBinding

class SendMoneyActivity : AppCompatActivity() {

    lateinit var binding: ActivitySendMoneyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySendMoneyBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}