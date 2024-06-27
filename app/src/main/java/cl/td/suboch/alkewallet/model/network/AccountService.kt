package cl.td.suboch.alkewallet.model.network

import cl.td.suboch.alkewallet.model.CuentaRequest
import cl.td.suboch.alkewallet.model.CuentaResponse
import cl.td.suboch.alkewallet.model.LoginRequest
import cl.td.suboch.alkewallet.model.LoginResponse
import cl.td.suboch.alkewallet.model.RegisterRequest
import cl.td.suboch.alkewallet.model.RegisterResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AccountService {
    @POST("users")
    fun crearUsuario(
        @Body usuarioCreado: RegisterRequest
    ): Call<RegisterResponse>

    @POST("accounts")
    fun crearCuenta(
        @Header("Authorization") authHeader: String,
        @Body accountRequest: CuentaRequest
    ): Call<CuentaResponse>

    @GET("accounts/me")
    fun obtenerCuenta(
        @Header("Authorization") authHeader: String
    ): Call<List<CuentaResponse>>


}