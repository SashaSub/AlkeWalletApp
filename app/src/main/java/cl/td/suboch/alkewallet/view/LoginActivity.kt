package cl.td.suboch.alkewallet.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import cl.td.suboch.alkewallet.HomeActivity
import cl.td.suboch.alkewallet.R
import cl.td.suboch.alkewallet.databinding.ActivityLoginBinding
import cl.td.suboch.alkewallet.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPreferences: SharedPreferences
    lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Configurar Binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Configurar ViewModel
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        //Vamos a implementar los SharedPreferences
        sharedPreferences = getSharedPreferences("Alke Wallet", MODE_PRIVATE)
        //Vamos a verificar si el usaurio ya me guardo el correo
        val correo = sharedPreferences.getString("correo_ingresado", null)
        //si el dato es distinto de null lo voy a prerrellenar
        if (correo != null){
            binding.txtEmail.setText(correo)
        }

        //Configurar onClick
        binding.buttonLogin.setOnClickListener {
            //Rescatar info que ingreso el usuario
            var correoIngresado = binding.txtEmail.text.toString()
            var passwordIngresado = binding.txtPassword.text.toString()
            //Guardar el correo en sharedPreferences
            val editor = sharedPreferences.edit()
            editor.putString("correo_ingresado", correoIngresado)
            editor.putBoolean("recuerdame", true)
            //to clean:
            //editor.clear()
            editor.apply()
            viewModel.hacerLogin(correoIngresado, passwordIngresado)
        }

        //COnfigurar observador, que observa el sujeto LoginResultLiveDAta
        viewModel.loginResultLiveData.observe(this){ loginOk ->
            if (loginOk == true) {
                val irMenuPrincipal = Intent(this, HomeActivity::class.java)
                startActivity(irMenuPrincipal)
            }else {
                Toast.makeText(this, "Datos Invalidos", Toast.LENGTH_LONG).show()
            }
        }

 /**
        //val crearCuenta = findViewById<Button>(R.id.button_crear_cuenta)
        binding.buttonCrearCuenta.setOnClickListener {
            val abrirPantallaCrear = Intent(this, SignupActivity::class.java)
            startActivity(abrirPantallaCrear)
        }

        //val fullTransfer = findViewById<Button>(R.id.button_login)
        binding.buttonLogin.setOnClickListener {
            val abrirPantallaTransferFull = Intent(this, HomePageActivity::class.java)
            startActivity(abrirPantallaTransferFull)
        }

        binding.buttonLogin.setOnClickListener {
            //val intent = Intent(this, ListActivity::class.java)
            //val intent = Intent(this, TabbedActivity::class.java)
            val intent = Intent(this, RecyclerViewActivity::class.java)
            startActivity(intent)
        }
*/

    }
}