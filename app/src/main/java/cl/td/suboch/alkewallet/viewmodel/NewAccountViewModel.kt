package cl.td.suboch.alkewallet.viewmodel

import android.icu.text.SimpleDateFormat
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.td.suboch.alkewallet.AlkeWalletApp
import cl.td.suboch.alkewallet.model.CuentaRequest
import cl.td.suboch.alkewallet.model.CuentaResponse
import cl.td.suboch.alkewallet.model.RegisterRequest
import cl.td.suboch.alkewallet.model.network.AccountService
import cl.td.suboch.alkewallet.model.network.RetrofitInstancia
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.Date
import java.util.Locale

class NewAccountViewModel : ViewModel() {

    val cuentaCreadaLiveData = MutableLiveData<Boolean>()
    val errorMsgLiveData = MutableLiveData<String>()

    fun assignAccountToUser(userId: Int) {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val currentDate = sdf.format(Date())

        val accountRequest = CuentaRequest(
            creationDate = currentDate,
            money = 5000.0,
            isBlocked = false,
            userId = userId
        )

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val token = AlkeWalletApp.tokenAccesso
                if (token.isNullOrEmpty()) {
                    errorMsgLiveData.postValue("Token no encontrado")
                    return@launch
                }
                val crearCuenta = RetrofitInstancia.retrofit.create(AccountService::class.java)
                val llamadaCreacionCuenta = crearCuenta.crearCuenta("Bearer $token", accountRequest)

                llamadaCreacionCuenta.enqueue(object: Callback<CuentaResponse>{
                    override fun onResponse(
                        call: Call<CuentaResponse>,
                        response: Response<CuentaResponse>
                    ) {
                        if (response.isSuccessful) {
                            cuentaCreadaLiveData.postValue(true)
                        }else {
                            cuentaCreadaLiveData.postValue(false)
                        }
                    }

                    override fun onFailure(call: Call<CuentaResponse>, t: Throwable) {
                        cuentaCreadaLiveData.postValue(false)
                    }
                })

            } catch (e: IOException) {
                // Log.e("AssignAccountVM", "Error de red: ${e.message}")
                cuentaCreadaLiveData.postValue(false)
                errorMsgLiveData.postValue("Error de red: ${e.message}")
//            } catch (e: Exception) {
//               // Log.e("AssignAccountVM", "Error desconocido: ${e.message}")
//                errorMsgLiveData.postValue("Error desconocido: ${e.message}")
//            }
            }
        }
    }
}