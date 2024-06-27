package cl.td.suboch.alkewallet.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.td.suboch.alkewallet.model.CreateAccount
import cl.td.suboch.alkewallet.model.RegisterRequest
import cl.td.suboch.alkewallet.model.RegisterResponse
import cl.td.suboch.alkewallet.model.network.AccountService
import cl.td.suboch.alkewallet.model.network.RetrofitInstancia
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {

    val registrationValid = MutableLiveData<Boolean>()
    // variable que almacena el resultado del registro
    val registerResultLiveData = MutableLiveData<Boolean>()

    fun registrarUsuario(first_name: String, last_name: String, email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                if (first_name.isNotEmpty() && last_name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                    val crearUsuario = RetrofitInstancia.retrofit.create(AccountService::class.java)
                    val llamadaCreacion = crearUsuario.crearUsuario(
                        RegisterRequest(
                            first_name,
                            last_name,
                            email,
                            password
                        )
                    )
                    //LLamar
                    llamadaCreacion.enqueue(object: Callback<RegisterResponse> {
                        override fun onResponse(
                            call: Call<RegisterResponse>,
                            response: Response<RegisterResponse>
                        ) {
                            if (response.isSuccessful) {
                                registrationValid.postValue(true)
                            } else {
                                registrationValid.postValue(false)
                            }
                        }
                        override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                            registrationValid.postValue(false)
                        }
                    })
                } else {
                    registrationValid.postValue(false)
                }
            } catch (e: Exception) {
                registrationValid.postValue(false)
            }
        }
    }
}