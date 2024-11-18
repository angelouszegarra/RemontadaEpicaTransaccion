package com.example.remontadaepicatransaccion.network  // Asegúrate de que el paquete sea el correcto

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Singleton para Retrofit
object RetrofitClient {

    // URL base de tu API, cámbiala por la correcta
    private const val BASE_URL = "https://api.tu-api.com/"

    // Instancia de Retrofit
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)  // Establece la URL base
        .addConverterFactory(GsonConverterFactory.create())  // Usamos Gson para convertir la respuesta JSON
        .build()

    // Instancia del servicio ApiService
    val apiService: ApiService = retrofit.create(ApiService::class.java)  // Usamos la interfaz ApiService
}
