package com.test.bank.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.test.bank.R
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.item_currency.view.*

class MainFragment: Fragment(R.layout.fragment_main)  {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCurrency()
    }

    private fun setCurrency(){
        val gbp = CardView(context!!)
        val eur = CardView(context!!)
        val rub = CardView(context!!)

        LayoutInflater.from(context).inflate(R.layout.item_currency, gbp, true)
        LayoutInflater.from(context).inflate(R.layout.item_currency, eur, true)
        LayoutInflater.from(context).inflate(R.layout.item_currency, rub, true)

        gbp.setBackgroundColor(resources.getColor(R.color.transparent))
        eur.setBackgroundColor(resources.getColor(R.color.transparent))
        rub.setBackgroundColor(resources.getColor(R.color.transparent))

        gbp.icr_symbol.text = resources.getString(R.string.pounds)
        eur.icr_symbol.text = resources.getString(R.string.euro)
        rub.icr_symbol.text = resources.getString(R.string.rubles)

        gbp.icr_currency.text = resources.getString(R.string.gbp)
        eur.icr_currency.text = resources.getString(R.string.eur)
        rub.icr_currency.text = resources.getString(R.string.rub)

        fm_ll_currency.addView(gbp)
        fm_ll_currency.addView(eur)
        fm_ll_currency.addView(rub)
    }
}