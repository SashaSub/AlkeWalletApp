package cl.td.suboch.alkewallet.model.network

import cl.td.suboch.alkewallet.model.LoginRequest
import cl.td.suboch.alkewallet.model.LoginResponse
import cl.td.suboch.alkewallet.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Api service con metodos para logera usuario
 */
interface LoginService {
    /**
     * Servicio para hacer login, recibe como parametros email y contraseña
     * encapsulados un objeto de Request
     */

    @POST("auth/login")
    fun hacerLogin(
        @Body request: LoginRequest
    ): Call<LoginResponse>

    /**
     * Servicio que obtiene la informacion del usuario que se logeo
     * Recibe como parametro el Token como header con la palabra Bearer
     */
    @GET("auth/me")
    fun obtenerInfoLogin(
        @Header("Authorization") token: String
    ): Call<User>


}