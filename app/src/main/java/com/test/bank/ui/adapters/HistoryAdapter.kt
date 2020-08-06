package com.test.bank.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.test.bank.R
import com.test.bank.model.HistoryInfo
import kotlinx.android.synthetic.main.item_history.view.*

class HistoryAdapter(private val history: ArrayList<HistoryInfo>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolderHistory>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderHistory {
        return ViewHolderHistory(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_history,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = history.size

    override fun onBindViewHolder(holder: ViewHolderHistory, position: Int) {
        holder.apply {
            name.text = history[position].name
            date.text = history[position].date
            symbol.text = history[position].symbol
            sumFloating.text = history[position].sumFloating
            sumStatic.text = history[position].sumStatic
            Picasso.get().load(history[position].img).error(R.drawable.img_placeholder_error)
                .into(holder.icon)
        }
    }

    fun update() {
        notifyDataSetChanged()
    }

    inner class ViewHolderHistory(view: View) : RecyclerView.ViewHolder(view) {

        val name: TextView = view.ih_name
        val date: TextView = view.ih_date
        val symbol: TextView = view.ih_floating_symbol
        val sumFloating: TextView = view.ih_floating_sum
        val sumStatic: TextView = view.ih_static_sum
        val icon: ImageView = view.ih_icon

        init {

        }
    }
}