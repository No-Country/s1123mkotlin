package com.nocountry.s1123mkotlin.farmacias

import android.content.Context
import com.nocuntry.s1123mkotlin.R
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun getRetrofit(context: Context): Retrofit {
    val apiKey = context.getString(R.string.openrouteservice_apikey)
    val interceptor = Interceptor { chain ->
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .header("Authorization", "Bearer $apiKey") // Agrega la clave de la API a la cabecera
        val request = requestBuilder.method(original.method, original.body).build()
        chain.proceed(request)
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    return Retrofit.Builder()
        .baseUrl("https://api.openrouteservice.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
}

