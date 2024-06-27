package cl.td.suboch.alkewallet.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cl.td.suboch.alkewallet.AlkeWalletApp
import cl.td.suboch.alkewallet.AlkeWalletApp.Companion.usuarioLogeado
import cl.td.suboch.alkewallet.R
import cl.td.suboch.alkewallet.view.adapter.TransactionAdapter
import cl.td.suboch.alkewallet.databinding.ActivityHomePageBinding
import cl.td.suboch.alkewallet.databinding.ActivityRecyclerViewBinding
import cl.td.suboch.alkewallet.databinding.SinCuentaBinding
import cl.td.suboch.alkewallet.viewmodel.HomePageViewModel
import cl.td.suboch.alkewallet.viewmodel.NewAccountViewModel
import cl.td.suboch.alkewallet.viewmodel.TransactionViewModel

class RecyclerViewActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomePageBinding

    //ActivityRecyclerViewBinding
    private lateinit var homePageViewModel: HomePageViewModel
    private lateinit var newAccountViewModel: NewAccountViewModel
    private lateinit var transactionViewModel: TransactionViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        //ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // !!! Check recyclerView !!!
        //val recyclerView: RecyclerView = findViewById(R.id.lista_recycler_home)
        // Access the RecyclerView using Binding
        val recyclerView: RecyclerView = binding.listaRecyclerHome

        // Inicializamos HomeViewModel
        homePageViewModel = ViewModelProvider(this).get(HomePageViewModel::class.java)
        newAccountViewModel = ViewModelProvider(this).get(NewAccountViewModel::class.java)
        transactionViewModel = ViewModelProvider(this).get(TransactionViewModel::class.java)

        if (usuarioLogeado != null) {
            binding.saludo.text = "Hola, ${usuarioLogeado?.first_name} ${usuarioLogeado?.last_name}"
        }

        // Set up the RecyclerView
  //      recyclerView.layoutManager = LinearLayoutManager(this)
  //      recyclerView.adapter = TransactionAdapter(transactions)
        //Configuramos el adaptador que se va a encagar de unir los datos con la vista
        // val adaptadorTransaction = TransactionAdapter(transactions.sortedArray())
        //  binding.listaRecycler.adapter = adaptadorTransaction

        // Set OnClickListener using View Binding
        binding.receiveMoney.setOnClickListener {
            // Handle button click
            // For example: show a toast
            //Toast.makeText(this, "Button clicked!", Toast.LENGTH_SHORT).show()
            val ingresarDinero = Intent(this, RequestMoneyActivity::class.java)
            startActivity(ingresarDinero)
        }
        binding.sendMoney.setOnClickListener {
            val enviarDinero = Intent(this, SendMoneyActivity::class.java)
            startActivity(enviarDinero)
        }
        binding.perfilIcon.setOnClickListener {
            val abrirPerfil = Intent(this, ProfileActivity::class.java)
            startActivity(abrirPerfil)
        }

        // Observamos los resultados de la verificación de la cuenta
        homePageViewModel.accountCheckLiveData.observe(this) { hasAccount ->
            if (hasAccount) {
                Toast.makeText(this, "Sí, tienes cuenta", Toast.LENGTH_SHORT).show()
            } else {
                sinCuentaAlert()
            }
        }

        homePageViewModel.userBalanceLiveData.observe(this) { balance ->
            binding.number.text = "$$balance"
        }

        homePageViewModel.errorMsgLiveData.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }

        newAccountViewModel.cuentaCreadaLiveData.observe(this) { cuentaCreada ->
            if (cuentaCreada) {
                Toast.makeText(this, "Cuenta creada exitosamente", Toast.LENGTH_SHORT).show()
                //aqui volveremos a cargar el homePageActivity
                val intent = Intent(this, HomePageActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Error al crear cuenta", Toast.LENGTH_SHORT).show()
            }
        }
        newAccountViewModel.errorMsgLiveData.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }

        transactionViewModel.transactionsLiveData.observe(this) { transactions ->
            if (transactions.isNotEmpty()) {
                val adapter = TransactionAdapter(transactions)
                binding.listaRecyclerHome.adapter = adapter
                binding.listaRecyclerHome.layoutManager = LinearLayoutManager(this)
                binding.noTransactionLayout.visibility = View.GONE
                binding.transactionLayout.visibility = View.VISIBLE
            } else {
                binding.noTransactionLayout.visibility = View.VISIBLE
                binding.transactionLayout.visibility = View.GONE
            }
        }

        transactionViewModel.errorMsgLiveData.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }

        // Verificar la cuenta del usuario
        homePageViewModel.checkUserAccount()

        // Obtener transacciones
        transactionViewModel.fetchTransactions()

    }
    private fun sinCuentaAlert() {
        val dialogBinding = SinCuentaBinding.inflate(LayoutInflater.from(this))
        val builder = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .setCancelable(false)
        val dialog = builder.create()
        dialogBinding.btnAccept.setOnClickListener {
            // Lógica para asignar una cuenta al usuario
            usuarioLogeado?.id?.let { userId ->
                newAccountViewModel.assignAccountToUser(userId)
            }
            dialog.dismiss()
        }
        dialog.show()
    }


    //crear lista de nombres
//    val transactions = arrayOf(
//        "Yara Khalil",
//        "Sara Ibrahim",
//        "Ahmad Ibrahim",
//        "Reem Khaled",
//        "Hiba Saleh"
//    )
}
