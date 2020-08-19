package com.test.bank.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.bank.R
import com.test.bank.model.CardInfo
import com.test.bank.repository.UserRepository
import kotlinx.android.synthetic.main.item_card.view.*

class CardsAdapter(private val cards: ArrayList<CardInfo>) :
    RecyclerView.Adapter<CardsAdapter.ViewHolderCards>() {

    private var callback: Callback? = null

    fun registerCallback(listener: Callback) {
        callback = listener
    }

    fun unregisterCallback() {
        callback = null
    }

    interface Callback {
        fun selectCard()
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
        holder.apply {
            number.text = cards[position].number
            icon.setImageResource(cards[position].imgId)
            if(UserRepository.selectedCard != cards[position].number)
                select.visibility = View.INVISIBLE
        }
    }

    fun update() {
        notifyDataSetChanged()
    }

    inner class ViewHolderCards(view: View) : RecyclerView.ViewHolder(view) {

        val number: TextView = view.ic_card_number
        val icon: ImageView = view.ic_card_img
        val select: ImageView = view.ic_card_pick

        init {
            view.setOnClickListener {
                UserRepository.selectedCard = cards[adapterPosition].number
                callback?.selectCard()
            }
        }
    }
}