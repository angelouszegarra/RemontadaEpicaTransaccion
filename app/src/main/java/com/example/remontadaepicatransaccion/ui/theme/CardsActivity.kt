package com.example.remontadaepicatransaccion.ui.theme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.remontadaepicatransaccion.R

class CardsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cards)

        val rvCards: RecyclerView = findViewById(R.id.rvCards)

        // Configurar RecyclerView
        rvCards.layoutManager = LinearLayoutManager(this)
        rvCards.adapter = CardsAdapter(getSampleCards())
    }

    private fun getSampleCards(): List<Card> {
        // Datos de ejemplo
        return listOf(
            Card("Visa", "**** **** **** 1234"),
            Card("MasterCard", "**** **** **** 5678")
        )
    }
}

data class Card(val type: String, val number: String)

class CardsAdapter(private val cards: List<Card>) : RecyclerView.Adapter<CardsAdapter.CardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = cards[position]
        holder.bind(card)
    }

    override fun getItemCount() = cards.size

    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(card: Card) {
            itemView.findViewById<TextView>(R.id.tvCardType).text = card.type
            itemView.findViewById<TextView>(R.id.tvCardNumber).text = card.number
        }
    }
}
