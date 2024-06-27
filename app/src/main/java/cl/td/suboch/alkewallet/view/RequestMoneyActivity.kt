package cl.td.suboch.alkewallet.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import cl.td.suboch.alkewallet.AlkeWalletApp
import cl.td.suboch.alkewallet.databinding.ActivityProfileBinding
import cl.td.suboch.alkewallet.databinding.ActivityRequestMoneyBinding
import cl.td.suboch.alkewallet.viewmodel.RequestMoneyViewModel

class RequestMoneyActivity : AppCompatActivity() {

    lateinit var binding: ActivityRequestMoneyBinding
    private lateinit var requestMoneyViewModel: RequestMoneyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequestMoneyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestMoneyViewModel = ViewModelProvider(this).get(RequestMoneyViewModel::class.java)

        // Obtener el nombre del usuario desde AlkeWalletApp
        val userName = "${AlkeWalletApp.usuarioLogeado?.first_name} ${AlkeWalletApp.usuarioLogeado?.last_name}"
        binding.userName.text = userName
        val userEmail = "${AlkeWalletApp.usuarioLogeado?.email}"
        binding.userEmail.text = userEmail

        binding.btnHome.setOnClickListener {
            val backHome = Intent(this, RecyclerViewActivity::class.java)
            startActivity(backHome)
        }

        binding.btnIngresarDinero.setOnClickListener {
            val notas = binding.agregarNotas.text.toString()
            val amountText = binding.amountIngresar.text.toString()

            if (notas.isNotEmpty() && amountText.isNotEmpty()) {
                val amount = amountText.toDoubleOrNull()
                val accountId = AlkeWalletApp.cuentaUsuario?.id // Aquí se debe obtener el accountId
                if (amount != null && accountId != null) {
                    requestMoneyViewModel.requestMoney(accountId, notas, amount)
                } else {
                    Toast.makeText(this, "Por favor, ingrese un monto válido", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(this, "Por favor, ingrese todos los datos", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        requestMoneyViewModel.topupResultLiveData.observe(this) { paymentResponse ->
            if (paymentResponse != null && paymentResponse.status == 200) {
                Toast.makeText(
                    this,
                    "Depósito realizado: ${paymentResponse.error}",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this, RecyclerViewActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Error al realizar el depósito", Toast.LENGTH_SHORT).show()
            }
        }

        requestMoneyViewModel.errorMsgLiveData.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

//    private fun goToHomePage() {
//        val intent = Intent(this, HomePageActivity::class.java)
//        startActivity(intent)
//        finish()
//    }
}