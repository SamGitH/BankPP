package com.test.bank.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.bank.model.Card
import com.test.bank.R
import kotlinx.android.synthetic.main.item_card.view.*

class CardsAdapter (private val cards: ArrayList<Card>) : RecyclerView.Adapter<CardsAdapter.ViewHolderCards>() {

    private var callback: Callback? = null

    fun registerCallback(listener: Callback) {
        callback = listener
    }

    fun unregisterCallback() {
        callback = null
    }

    interface Callback {
        fun getNativeNameFromAdapter(nativeName: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCards {
        return ViewHolderCards(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_card,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = cards.size

    override fun onBindViewHolder(holder: ViewHolderCards, position: Int) {
        holder.number.text = cards[position].number
    }

    fun update() {
        notifyDataSetChanged()
    }

    inner class ViewHolderCards(view: View) : RecyclerView.ViewHolder(view) {

        val number: TextView = view.ic_card_number

        init {

        }
    }
}