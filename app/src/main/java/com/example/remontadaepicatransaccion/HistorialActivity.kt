package com.example.remontadaepicatransaccion

import AppDatabase
import TransaccionDao
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistorialActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var transaccionDao: TransaccionDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)

        recyclerView = findViewById(R.id.rvHistorial)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Obtén la base de datos y el DAO
        val db = databaseBuilder(applicationContext, AppDatabase::class.java, "nombre_db").build()
        transaccionDao = db.transaccionDao()

        // Cargar datos en un hilo secundario
        CoroutineScope(Dispatchers.IO).launch {
            val transacciones = transaccionDao.obtenerTransacciones()

            // Mostrar datos en el hilo principal
            withContext(Dispatchers.Main) {
                val adapter = TransaccionAdapter(transacciones)
                recyclerView.adapter = adapter
            }
        }
    }
}
