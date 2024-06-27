package cl.td.suboch.alkewallet.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import cl.td.suboch.alkewallet.databinding.ActivityProfileBinding
import cl.td.suboch.alkewallet.databinding.ActivitySendMoneyBinding
import cl.td.suboch.alkewallet.viewmodel.SendMoneyViewModel

class SendMoneyActivity : AppCompatActivity() {

    lateinit var binding: ActivitySendMoneyBinding
    private lateinit var sendMoneyViewModel: SendMoneyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySendMoneyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sendMoneyViewModel = ViewModelProvider(this).get(SendMoneyViewModel::class.java)

        binding.backHome.setOnClickListener {
            val backHome = Intent(this, RecyclerViewActivity::class.java)
            startActivity(backHome)
        }

        binding.btnSend.setOnClickListener {
            val concept = binding.agregarNotasEnvio.text.toString()
            val amountText = binding.editTextNumberDecimal.text.toString()

            if (concept.isNotEmpty() && amountText.isNotEmpty()) {
                val amount = amountText.toDoubleOrNull()
                if (amount != null) {
                    sendMoneyViewModel.sendMoney(concept, amount)
                } else {
                    Toast.makeText(this, "Por favor, ingrese un monto válido", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, ingrese todos los datos", Toast.LENGTH_SHORT).show()
            }
        }

        sendMoneyViewModel.paymentResultLiveData.observe(this) { paymentResponse ->
            if (paymentResponse != null && paymentResponse.message == "OK") {
                Toast.makeText(this, "Transferencia realizada", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, RecyclerViewActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Error al enviar el pago", Toast.LENGTH_SHORT).show()
            }
        }

        sendMoneyViewModel.errorLiveData.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }
}