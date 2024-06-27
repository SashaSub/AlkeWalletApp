package cl.td.suboch.alkewallet.model.network

import cl.td.suboch.alkewallet.model.PaymentRequest
import cl.td.suboch.alkewallet.model.PaymentResponse
import cl.td.suboch.alkewallet.model.TransactionResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface TransactionService {

    @GET("transactions")
    fun getTransactions(@Header("Authorization") authHeader: String): Call<TransactionResponse>

    @POST("accounts/{accountId}")
    fun sendPayment(
        @Header("Authorization") authHeader: String,
        @Path("accountId") accountId: Int,
        @Body paymentRequest: PaymentRequest
    ): Call<PaymentResponse>
}