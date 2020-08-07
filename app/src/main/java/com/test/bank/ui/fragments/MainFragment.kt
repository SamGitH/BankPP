package com.test.bank.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.test.bank.R
import com.test.bank.model.CardInfo
import com.test.bank.model.CardType
import com.test.bank.model.CurrencyCardView
import com.test.bank.ui.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.item_currency.view.*

class MainFragment : Fragment(R.layout.fragment_main) {

    private val itemsCurrency = ArrayList<CurrencyCardView>()
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.card.observe(viewLifecycleOwner, Observer {
            fm_mc.card = CardInfo(
                it.number,
                it.cardHolder,
                it.valid,
                it.balance.usd,
                it.balance.gbp,
                getIcon(it.type)
            )
        })
        setCurrency()
        drawSelectedCurrency()
    }

    private fun getIcon(type: CardType): Int = when (type) {
        CardType.MASTER_CARD -> R.drawable.img_master_card
        CardType.VISA -> R.drawable.img_visa
        CardType.UNION_PAY -> R.drawable.img_union_pay
        CardType.DEFAULT -> R.drawable.img_union_pay//fixme
    }


    private fun setCurrency() {

        for (i in 0..2) {
            itemsCurrency.add(CurrencyCardView(CardView(context!!), false))
            LayoutInflater.from(context).inflate(R.layout.item_currency, itemsCurrency[i].cv, true)
            itemsCurrency[i].cv.setBackgroundColor(resources.getColor(R.color.transparent))
            fm_ll_currency.addView(itemsCurrency[i].cv)
            itemsCurrency[i].cv.setOnClickListener {
                currencyClick(itemsCurrency[i])
            }
        }

        itemsCurrency[0].selected = true

        itemsCurrency[0].cv.icr_symbol.text = resources.getString(R.string.pounds)
        itemsCurrency[1].cv.icr_symbol.text = resources.getString(R.string.euro)
        itemsCurrency[2].cv.icr_symbol.text = resources.getString(R.string.rubles)

        itemsCurrency[0].cv.icr_currency.text = resources.getString(R.string.gbp)
        itemsCurrency[1].cv.icr_currency.text = resources.getString(R.string.eur)
        itemsCurrency[2].cv.icr_currency.text = resources.getString(R.string.rub)

    }

    private fun currencyClick(cv: CurrencyCardView) {
        if (cv.selected)
            return

        itemsCurrency.forEach {
            it.selected = false
        }

        cv.selected = true

        drawSelectedCurrency()
    }

    private fun drawSelectedCurrency() {
        itemsCurrency.forEach {
            if (it.selected) {
                it.cv.icr_ll.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.selected_currency
                    )
                )//fixme
                it.cv.icr_symbol.setTextColor(resources.getColor(R.color.item))
                it.cv.icr_currency.setTextColor(resources.getColor(R.color.item))
            } else {
                it.cv.icr_ll.setBackgroundColor(resources.getColor(R.color.item))
                it.cv.icr_symbol.setTextColor(resources.getColor(R.color.text_color_grey))
                it.cv.icr_currency.setTextColor(resources.getColor(R.color.text_color_grey))
            }
        }
    }

}