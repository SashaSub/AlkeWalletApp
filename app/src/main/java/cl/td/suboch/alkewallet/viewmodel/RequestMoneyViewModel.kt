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

class RequestMoneyViewModel : ViewModel() {

    val topupResultLiveData = MutableLiveData<PaymentResponse?>()
    val errorMsgLiveData = MutableLiveData<String>()
    fun requestMoney(accountId: Int, concept: String, amount: Double) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val token = AlkeWalletApp.tokenAccesso
                if (token.isNullOrEmpty()) {
                    errorMsgLiveData.postValue("Token no encontrado")
                    return@launch
                }

                val paymentRequest = PaymentRequest(type = "topup", concept = concept, amount = amount)

                val enviarDinero = RetrofitInstancia.retrofit.create(TransactionService::class.java)
                val envioLlamada = enviarDinero.sendPayment("Bearer $token", accountId, paymentRequest)

                envioLlamada.enqueue(object: Callback<PaymentResponse> {
                    override fun onResponse(
                        call: Call<PaymentResponse>,
                        response: Response<PaymentResponse>
                    ) {
                        if(response.isSuccessful) {
                            val paymentResponse = response.body()
                            topupResultLiveData.postValue(paymentResponse)
                        } else {
                            val errorBody = response.errorBody()?.string()
                        //    Log.e("RequestMoneyViewModel", "Error al realizar el depósito: $errorBody")
                            errorMsgLiveData.postValue("Error al realizar el depósito: $errorBody")
                        }
                    }
                    override fun onFailure(p0: Call<PaymentResponse>, p1: Throwable) {
                        errorMsgLiveData.postValue("Error al realizar el depósito")
                    }
                })

            //    val response = ApiClient.apiService.sendPayment("Bearer $token", accountId, paymentRequest)

            } catch (e: IOException) {
                Log.e("RequestMoneyViewModel", "Error de red: ${e.message}")
                errorMsgLiveData.postValue("Error de red: ${e.message}")
            } catch (e: Exception) {
                Log.e("RequestMoneyViewModel", "Error desconocido: ${e.message}")
                errorMsgLiveData.postValue("Error desconocido: ${e.message}")
            }
        }
    }
}