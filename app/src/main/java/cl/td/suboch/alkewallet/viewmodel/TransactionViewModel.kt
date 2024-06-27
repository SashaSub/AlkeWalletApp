package cl.td.suboch.alkewallet.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.td.suboch.alkewallet.AlkeWalletApp
import cl.td.suboch.alkewallet.AlkeWalletApp.Companion.tokenAccesso
import cl.td.suboch.alkewallet.model.Transaction
import cl.td.suboch.alkewallet.model.TransactionResponse
import cl.td.suboch.alkewallet.model.User
import cl.td.suboch.alkewallet.model.network.LoginService
import cl.td.suboch.alkewallet.model.network.RetrofitInstancia
import cl.td.suboch.alkewallet.model.network.TransactionService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import java.io.IOException

class TransactionViewModel : ViewModel() {

    val transactionsLiveData = MutableLiveData<List<Transaction>>()
    val errorMsgLiveData = MutableLiveData<String>()

    fun fetchTransactions() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val token = AlkeWalletApp.tokenAccesso
                if (token.isNullOrEmpty()) {
                    errorMsgLiveData.postValue("Token no encontrado")
                    return@launch
                }

                val obtenerTransaction = RetrofitInstancia.retrofit.create(TransactionService::class.java)
                val transactionLlamada = obtenerTransaction.getTransactions("Bearer $token")

                transactionLlamada.enqueue(object: Callback<TransactionResponse> {
                    override fun onResponse(
                        call: Call<TransactionResponse>,
                        response: Response<TransactionResponse>
                    ) {
                        if (response.isSuccessful) {
                            val transactionsResponse = response.body()
                            val transactions = transactionsResponse?.data
                            Log.d("TransactionViewModel", "Transacciones obtenidas: $transactions")
                            transactionsLiveData.postValue(transactions?: emptyList())
                        } else {
                            Log.e("TransactionViewModel", "Error al obtener las transacciones")
                            errorMsgLiveData.postValue("Error al obtener las transacciones")
                        }
                    }
                    override fun onFailure(p0: Call<TransactionResponse>, p1: Throwable) {
                        TODO("Not yet implemented")
                    }
                })
            } catch (e: IOException) {
                Log.e("TransactionViewModel", "Error de red: ${e.message}")
                errorMsgLiveData.postValue("Error de red: ${e.message}")
            } catch (e: Exception) {
                Log.e("TransactionViewModel", "Error desconocido: ${e.message}")
                errorMsgLiveData.postValue("Error desconocido: ${e.message}")
            }
        }
    }
}