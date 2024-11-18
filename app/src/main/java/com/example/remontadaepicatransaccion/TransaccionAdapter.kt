package com.example.remontadaepicatransaccion

import Transaccion
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TransaccionAdapter(private val transacciones: List<Transaccion>) : RecyclerView.Adapter<TransaccionAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val montoTextView: TextView = itemView.findViewById(R.id.tvMonto)
        val fechaTextView: TextView = itemView.findViewById(R.id.tvFecha)
        val receptorTextView: TextView = itemView.findViewById(R.id.tvReceptor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_transaccion, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaccion = transacciones[position]
        holder.montoTextView.text = transaccion.monto.toString()
        holder.fechaTextView.text = transaccion.fecha
        holder.receptorTextView.text = transaccion.receptor
    }

    override fun getItemCount(): Int {
        return transacciones.size
    }
}
