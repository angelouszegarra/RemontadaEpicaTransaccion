package com.example.remontadaepicatransaccion  // Asegúrate de que este paquete coincida con el tuyo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.remontadaepicatransaccion.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class TransferActivity : AppCompatActivity() {

    // Definimos las vistas
    private lateinit var etReceiverPhone: EditText
    private lateinit var etAmount: EditText
    private lateinit var btnTransfer: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer)  // Asegúrate de tener el diseño XML correcto

        // Inicializamos las vistas
        etReceiverPhone = findViewById(R.id.etReceiverPhone)
        etAmount = findViewById(R.id.etAmount)
        btnTransfer = findViewById(R.id.btnTransfer)

        // Configuramos el clic del botón para hacer la transferencia
        btnTransfer.setOnClickListener {
            val phone = etReceiverPhone.text.toString()
            val amount = etAmount.text.toString().toDoubleOrNull()

            if (amount != null && phone.isNotEmpty()) {
                obtenerTarifa(amount)  // Llamamos al método que obtiene la tarifa
            } else {
                Toast.makeText(this, "Por favor ingresa el número de teléfono y monto", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Método para obtener la tarifa usando Retrofit
    private fun obtenerTarifa(monto: Double) {
        lifecycleScope.launch {
            try {
                // Hacemos la solicitud a la API usando Retrofit
                val response = RetrofitClient.apiService.getTarifa(monto)

                if (response.isSuccessful) {
                    // Si la respuesta es exitosa, mostramos la tarifa
                    val tarifa = response.body()?.tarifa
                    Toast.makeText(this@TransferActivity, "Tarifa: $tarifa", Toast.LENGTH_LONG).show()

                    // Aquí podrías proceder a la transferencia, mostrando la tarifa y confirmando el pago
                } else {
                    // Si la respuesta no es exitosa, mostramos un error
                    Toast.makeText(this@TransferActivity, "Error en la respuesta", Toast.LENGTH_LONG).show()
                }
            } catch (e: HttpException) {
                // Manejo de error en la solicitud HTTP
                Toast.makeText(this@TransferActivity, "Error de red", Toast.LENGTH_LONG).show()
            } catch (e: Throwable) {
                // Manejo de cualquier otro tipo de error
                Toast.makeText(this@TransferActivity, "Error desconocido", Toast.LENGTH_LONG).show()
            }
        }
    }
}
