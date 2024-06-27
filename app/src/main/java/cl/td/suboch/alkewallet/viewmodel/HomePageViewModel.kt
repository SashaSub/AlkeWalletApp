package cl.td.suboch.alkewallet.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.td.suboch.alkewallet.AlkeWalletApp
import cl.td.suboch.alkewallet.model.CuentaResponse
import cl.td.suboch.alkewallet.model.LoginResponse
import cl.td.suboch.alkewallet.model.network.AccountService
import cl.td.suboch.alkewallet.model.network.RetrofitInstancia
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class HomePageViewModel : ViewModel() {

    val accountCheckLiveData = MutableLiveData<Boolean>()
    val errorMsgLiveData = MutableLiveData<String?>()
    val userBalanceLiveData = MutableLiveData<Double?>()
    fun checkUserAccount() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val token = AlkeWalletApp.tokenAccesso
                if (token.isNullOrEmpty()) {
                    errorMsgLiveData.postValue("Token no encontrado")
                    return@launch
                }

                val conectCuenta: AccountService = RetrofitInstancia.retrofit.create(AccountService::class.java)
                val llamadaObtenerCuenta: Call<List<CuentaResponse>> = conectCuenta.obtenerCuenta("Bearer $token")

                llamadaObtenerCuenta.enqueue(object: Callback<List<CuentaResponse>> {
                    override fun onResponse(
                        call: Call<List<CuentaResponse>>,
                        response: Response<List<CuentaResponse>>
                    ) {
                        if (response.isSuccessful) {
                            val cuentaData = response.body()
                            if (cuentaData != null && cuentaData.isNotEmpty()) {
                                AlkeWalletApp.cuentaUsuario =
                                    cuentaData[0] // Guardar la cuenta del usuario en AlkeWalletApp
                                accountCheckLiveData.postValue(true)
                                userBalanceLiveData.postValue(cuentaData[0].money) // Asume que la primera cuenta es la actual
                            } else {
                                accountCheckLiveData.postValue(false)
                            }
                        } else {
                            accountCheckLiveData.postValue(false)
                        }
                    }
                    override fun onFailure(call: Call<List<CuentaResponse>>, t: Throwable) {
                        accountCheckLiveData.postValue(false)
                        errorMsgLiveData.postValue(null)
                    }
                })




            } catch (e: IOException) {
                accountCheckLiveData.postValue(false)
                errorMsgLiveData.postValue(null)
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error desconocido: ${e.message}")
                errorMsgLiveData.postValue("Error desconocido: ${e.message}")
            }
        }
    }
}