package com.example.remontadaepicatransaccion.network

// Clase de datos para manejar la respuesta de la API
data class TarifaResponse(
    val tarifa: Double,  // El valor de la tarifa calculada por la API
    val mensaje: String  // Un mensaje opcional que puede devolver la API
)