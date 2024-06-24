package cl.td.suboch.alkewallet.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstancia {

    companion object {
//        val intercepter = HttpLoggingInterceptor().apply {
//            this.level = HttpLoggingInterceptor.Level.BODY
//        }
//        val client = OkHttpClient.Builder().apply {
//            this.addInterceptor(intercepter)
//        }.build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://wallet-main.eba-ccwdurgr.us-east-1.elasticbeanstalk.com/")
            //.client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}