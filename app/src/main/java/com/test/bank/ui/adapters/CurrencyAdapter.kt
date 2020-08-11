package com.test.bank.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.test.bank.R
import com.test.bank.model.CurrencyItem
import kotlinx.android.synthetic.main.item_currency.view.*

class CurrencyAdapter(
    private val currencyItems: ArrayList<CurrencyItem>
) : RecyclerView.Adapter<CurrencyAdapter.ViewHolderCurrency>() {

    private var callback: Callback? = null

    fun registerCallback(listener: Callback) {
        callback = listener
    }

    fun unregisterCallback() {
        callback = null
    }

    interface Callback {
        fun setSelectedColors(view: View)
        fun setDisableColors(view: View)
        fun resetItemsColors(id: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCurrency {
        return ViewHolderCurrency(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_currency,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = currencyItems.size

    override fun onBindViewHolder(holder: ViewHolderCurrency, position: Int) {
        holder.apply {
            symbol.text = currencyItems[position].symbol
            currency.text = currencyItems[position].text
            if (currencyItems[position].selected)
                callback?.setSelectedColors(itemView)
            else
                callback?.setDisableColors(itemView)
        }
    }

    fun update() {
        notifyDataSetChanged()
    }

    inner class ViewHolderCurrency(view: View) : RecyclerView.ViewHolder(view) {

        val symbol: TextView = view.icr_symbol
        val currency: TextView = view.icr_currency

        init {
            view.setOnClickListener {
                callback?.resetItemsColors(adapterPosition)
            }
        }
    }
}