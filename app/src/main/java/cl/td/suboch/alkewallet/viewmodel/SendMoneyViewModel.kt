package cl.td.suboch.alkewallet.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.td.suboch.alkewallet.AlkeWalletApp
import cl.td.suboch.alkewallet.model.PaymentRequest
import cl.td.suboch.alkewallet.model.PaymentResponse
import cl.td.suboch.alkewallet.model.network.RetrofitInstancia
import cl.td.suboch.alkewallet.model.network.TransactionService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class SendMoneyViewModel : ViewModel() {
    val paymentResultLiveData = MutableLiveData<PaymentResponse?>()
    val errorLiveData = MutableLiveData<String>()
    fun sendMoney(concept: String, amount: Double) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val token = AlkeWalletApp.tokenAccesso
                if (token.isNullOrEmpty()) {
                    errorLiveData.postValue("Token no encontrado")
                    return@launch
                }

                val paymentRequest = PaymentRequest(type = "payment", concept = concept, amount = amount)

                val enviarDinero = RetrofitInstancia.retrofit.create(TransactionService::class.java)
                val llamadaEnviar = enviarDinero.sendPayment("Bearer $token", 1916, paymentRequest)

                llamadaEnviar.enqueue(object: Callback<PaymentResponse> {
                    override fun onResponse(
                        call: Call<PaymentResponse>,
                        response: Response<PaymentResponse>
                    ) {
                        if (response.isSuccessful) {
                            val paymentResponse = response.body()
                            paymentResultLiveData.postValue(paymentResponse)
                        } else {
                            val errorBody = response.errorBody()?.string()
                            Log.e("SendMoneyViewModel", "Error al enviar el pago: $errorBody")
                            errorLiveData.postValue("Error al enviar el pago: $errorBody")
                        }
                    }
                    override fun onFailure(p0: Call<PaymentResponse>, p1: Throwable) {
                        errorLiveData.postValue("Error desconocido")
                    }

                })

             //   val response = ApiClient.apiService.sendPayment("Bearer $token", 2163, paymentRequest)

            } catch (e: IOException) {
                Log.e("SendMoneyViewModel", "Error de red: ${e.message}")
                errorLiveData.postValue("Error de red: ${e.message}")
            } catch (e: Exception) {
                Log.e("SendMoneyViewModel", "Error desconocido: ${e.message}")
                errorLiveData.postValue("Error desconocido: ${e.message}")
            }
        }
    }

}