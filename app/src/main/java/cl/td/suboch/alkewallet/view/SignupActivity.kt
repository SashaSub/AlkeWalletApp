package cl.td.suboch.alkewallet.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cl.td.suboch.alkewallet.R
import cl.td.suboch.alkewallet.databinding.ActivitySignupBinding
import cl.td.suboch.alkewallet.viewmodel.RegisterViewModel

class SignupActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignupBinding

    //declaramos el viewModel
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Inicializamos el viewModel
        viewModel = RegisterViewModel()

        //  val login = findViewById<Button>(R.id.button_logear)
        binding.buttonLogear.setOnClickListener { pantallaLogin() }
        binding.buttonLogin.setOnClickListener {
            register()
            pantallaLogin()
        }
        // Observamos el resultado del registro
        viewModel.registerResultLiveData.observe(this) { registerOk ->
            if (registerOk) {
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_LONG).show()
                pantallaLogin()
            } else {
                Toast.makeText(this, "Error en el registro", Toast.LENGTH_LONG).show()
            }
        }
        //val emptyTransfer = findViewById<Button>(R.id.button_login)
//        binding.buttonLogin.setOnClickListener {
//            val homePageEmpty = Intent(this, HomePageEmptyActivity::class.java)
//            startActivity(homePageEmpty)
//        }
    }
    private fun register() {
        val firstName = binding.inputName.text.toString()
        val lastName = binding.inputLastName.text.toString()
        val email = binding.inputEmail.text.toString()
        val password = binding.inputPass.text.toString()
        // Llama al ViewModel para registrar al usuario
        viewModel.registrarUsuario(firstName, lastName, email, password)
    }
    private fun pantallaLogin() {
        val abrirPantallaLogin = Intent(this, LoginActivity::class.java)
        startActivity(abrirPantallaLogin)
    }
}