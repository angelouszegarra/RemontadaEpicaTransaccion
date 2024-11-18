package com.example.remontadaepicatransaccion.network  // Asegúrate de que el paquete sea el correcto

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// Interfaz para Retrofit que define los endpoints de la API
interface ApiService {

    // Esta función obtiene la tarifa usando el monto de la transferencia
    @GET("tarifas")  // Cambia esta URL según el endpoint real de la API
    suspend fun getTarifa(@Query("monto") amount: Double): Response<TarifaResponse>
}
