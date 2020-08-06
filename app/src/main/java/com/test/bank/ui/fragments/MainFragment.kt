package com.test.bank.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.test.bank.R
import com.test.bank.model.CurrencyCardView
import com.test.bank.ui.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.item_currency.view.*

class MainFragment : Fragment(R.layout.fragment_main) {

    private val itemsCurrency = ArrayList<CurrencyCardView>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCurrency()
        drawSelectedCurrency()
    }

    private fun setCurrency() {

        for (i in 0..2) {
            itemsCurrency.add(CurrencyCardView(CardView(context!!), false))
            LayoutInflater.from(context).inflate(R.layout.item_currency, itemsCurrency[i].cv, true)
            itemsCurrency[i].cv.setBackgroundColor(resources.getColor(R.color.transparent))
            fm_ll_currency.addView(itemsCurrency[i].cv)
            itemsCurrency[i].cv.setOnClickListener {

            }
        }

        itemsCurrency[0].selected = true

        itemsCurrency[0].cv.icr_symbol.text = resources.getString(R.string.pounds)
        itemsCurrency[1].cv.icr_symbol.text = resources.getString(R.string.euro)
        itemsCurrency[2].cv.icr_symbol.text = resources.getString(R.string.rubles)

        itemsCurrency[0].cv.icr_currency.text = resources.getString(R.string.gbp)
        itemsCurrency[1].cv.icr_currency.text = resources.getString(R.string.eur)
        itemsCurrency[2].cv.icr_currency.text = resources.getString(R.string.rub)

//        val gbp = CardView(context!!)
//        val eur = CardView(context!!)
//        val rub = CardView(context!!)
//
//
//
//        LayoutInflater.from(context).inflate(R.layout.item_currency, gbp, true)
//        LayoutInflater.from(context).inflate(R.layout.item_currency, eur, true)
//        LayoutInflater.from(context).inflate(R.layout.item_currency, rub, true)
//
//        gbp.setBackgroundColor(resources.getColor(R.color.transparent))
//        eur.setBackgroundColor(resources.getColor(R.color.transparent))
//        rub.setBackgroundColor(resources.getColor(R.color.transparent))
//
//        gbp.icr_symbol.text = resources.getString(R.string.pounds)
//        eur.icr_symbol.text = resources.getString(R.string.euro)
//        rub.icr_symbol.text = resources.getString(R.string.rubles)
//
//        gbp.icr_currency.text = resources.getString(R.string.gbp)
//        eur.icr_currency.text = resources.getString(R.string.eur)
//        rub.icr_currency.text = resources.getString(R.string.rub)
//
//        fm_ll_currency.addView(gbp)
//        fm_ll_currency.addView(eur)
//        fm_ll_currency.addView(rub)

    }

    private fun currencyClick (cv: CurrencyCardView){
        if (cv.selected)
            return

        itemsCurrency.forEach {
            it.selected = false
        }

        cv.selected = true

        drawSelectedCurrency()
    }

    private fun drawSelectedCurrency(){
        itemsCurrency.forEach {
            if(it.selected){
//                it.cv.setBackgroundColor()
            }
            else {

            }
        }
    }

}