package cl.td.suboch.alkewallet.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.td.suboch.alkewallet.AlkeWalletApp.Companion.tokenAccesso
import cl.td.suboch.alkewallet.model.LoginRequest
import cl.td.suboch.alkewallet.model.LoginResponse
import cl.td.suboch.alkewallet.model.User
import cl.td.suboch.alkewallet.model.network.LoginService
import cl.td.suboch.alkewallet.model.network.RetrofitInstancia
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

/**
 * ViewModel encargado de hacer el login de la app
 */
class LoginViewModel : ViewModel() {

    //Variable LiveData que va a informar a la vista el login
    //val loginResultLiveData = MutableLiveData<Boolean>()
    //Variable LiveData que va a informar el token al login
    val tokenLiveData = MutableLiveData<String?>()
    //Variable LiveData que va a enviar el mensaje de error
    val errorLiveData = MutableLiveData<String?>()
    //Variable LiveData que va a informar cuando tenga la informacion del usuario
    val usuarioLiveData = MutableLiveData<User?>()

    /**
     * funcion que implementa una corrrutina para llamar a la Api
     */
    fun hacerLogin(email: String, contrasena: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                //Aca nosotros vamos a llamar a la API
                val login: LoginService = RetrofitInstancia.retrofit.create(LoginService::class.java)
               // login.hacerLogin(LoginRequest(email, contrasena))

                val llamadaApi: Call<LoginResponse> = login.hacerLogin(
                    LoginRequest(email, contrasena)
                )

                llamadaApi.enqueue(object : Callback<LoginResponse>{
                    //Respuesta cuando la informacion cargo correctamente
                    override fun onResponse(call: Call<LoginResponse>,
                                            response: Response<LoginResponse>) {
                        //Si la respues tiene un codigo entre el 200 y el 300 de HTTP
                        if(response.isSuccessful){
                            //Aca yo rescato la data que me devolvio el servicio ya tranformado desde JSON a un objeto
                            val respuesta : LoginResponse? = response.body()
                            //Como se logeo correctamente si me trae el dato del Token
                            if (respuesta?.accessToken != null) {
                                //loginResultLiveData.postValue(true)
                                tokenLiveData.postValue(respuesta.accessToken)
                                errorLiveData.postValue(null)
                                //Si no me logeo entonces muestro un error o muestro la respuesta
                            }else{
                                //si hay un error se ejecuta este codigo
                                //loginResultLiveData.postValue(false)
                                tokenLiveData.postValue(null)
                                errorLiveData.postValue(respuesta?.error)
                            }
                        }else{
                            val respuestaError : LoginResponse? = response.body()
                            //loginResultLiveData.postValue(false)
                            tokenLiveData.postValue(null)
                            errorLiveData.postValue(respuestaError?.error)
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        tokenLiveData.postValue(null)
                        errorLiveData.postValue(null)
                    }

                })
             //   if (email == "test@test.cl" && contrasena == "1234") {
             //       loginResultLiveData.postValue(true)
             //   } else {
             //       loginResultLiveData.postValue(false)
             //   }


            } catch (e: Exception) {
                //aqui si hay un error se ejecuta este codigo
                //loginResultLiveData.postValue(false)
                tokenLiveData.postValue(null)
                errorLiveData.postValue(null)
            }
        }
    }

    /**
     * funcion para obtener la info del usuario
     */

    fun obtenerDatosUser(){
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val getUsuario = RetrofitInstancia.retrofit.create(LoginService::class.java)
                val token = "Bearer $tokenAccesso"
                val usuarioLlamada : Call<User> = getUsuario.obtenerInfoLogin(token)
                usuarioLlamada.enqueue(object : Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if (response.isSuccessful) {
                            val usuarioLogin = response.body()
                            usuarioLiveData.postValue(usuarioLogin)
                        } else {
                            usuarioLiveData.postValue(null)
                        }
                    }
                    override fun onFailure(call: Call<User>, t: Throwable) {
                        usuarioLiveData.postValue(null)
                    }
                })

            } catch(e: Exception) {
                //aqui si hay un error se ejecuta este codigo
                usuarioLiveData.postValue(null)
            }
        }
    }

}