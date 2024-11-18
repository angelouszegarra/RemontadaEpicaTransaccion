package com.example.remontadaepicatransaccion.ui.theme

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.remontadaepicatransaccion.R

class CurrencySelectorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_selector)

        val spinnerCurrencies: Spinner = findViewById(R.id.spinnerCurrencies)
        val btnConfirmCurrency: Button = findViewById(R.id.btnConfirmCurrency)

        val currencies = listOf("USD - Dólar", "EUR - Euro", "PEN - Soles")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCurrencies.adapter = adapter

        btnConfirmCurrency.setOnClickListener {
            val selectedCurrency = spinnerCurrencies.selectedItem.toString()
            Toast.makeText(this, "Moneda seleccionada: $selectedCurrency", Toast.LENGTH_SHORT).show()
        }
    }
}
