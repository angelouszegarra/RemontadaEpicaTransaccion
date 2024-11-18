package com.example.remontadaepicatransaccion.ui.theme

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.remontadaepicatransaccion.R
import java.text.NumberFormat

class FeesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fees)

        val etTransferAmount: EditText = findViewById(R.id.etTransferAmount)
        val tvCalculatedFee: TextView = findViewById(R.id.tvCalculatedFee)
        val btnCalculateFee: Button = findViewById(R.id.btnCalculateFee)

        btnCalculateFee.setOnClickListener {
            val amountText = etTransferAmount.text.toString()

            if (amountText.isNotEmpty()) {
                val amount = amountText.toDoubleOrNull()
                if (amount != null && amount > 0) {
                    val fee = calculateFee(amount)
                    val formattedFee = NumberFormat.getCurrencyInstance().format(fee)
                    tvCalculatedFee.text = "Tarifa estimada: $formattedFee"
                } else {
                    Toast.makeText(this, "Por favor, ingresa un monto válido.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "El campo de monto está vacío.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun calculateFee(amount: Double): Double {
        val feePercentage = 0.025 // 2.5% tarifa
        return amount * feePercentage
    }
}
