package com.test.bank.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.bank.R
import com.test.bank.model.CardInfo
import com.test.bank.model.CardType
import com.test.bank.model.CurrencyItem
import com.test.bank.model.HistoryInfo
import com.test.bank.ui.adapters.CurrencyAdapter
import com.test.bank.ui.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.item_currency.view.*

class MainFragment : Fragment(R.layout.fragment_main), CurrencyAdapter.Callback {

    private val itemsCurrency = ArrayList<CurrencyItem>()

    private val adapter: CurrencyAdapter by lazy {
        CurrencyAdapter(itemsCurrency)
    }

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setItemCurrency()
        bind()
    }

    override fun onStart() {
        super.onStart()
        adapter.update()
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter.unregisterCallback()
    }

    override fun onResume() {
        super.onResume()
        viewModel.changeCard()
        fm_hv.adapter.update()
    }

    private fun bind() {
        fm_rv.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = this@MainFragment.adapter
        }
        adapter.registerCallback(this)

        fm_mc.setOnClickListener {
            findNavController().navigate(R.id.myCardFragment)
        }

        viewModel.card.observe(viewLifecycleOwner, Observer { card ->
            fm_mc.card = CardInfo(
                card.number,
                card.cardHolder,
                card.valid,
                card.balance.usd,
                card.balance.gbp,
                getIcon(card.type)
            )

            fm_hv.history.addAll(card.history.map {
                HistoryInfo(
                    it.title,
                    it.date,
                    it.amount.usd,
                    "",
                    "",
                    it.icon_url
                )
            }) //fixme
            fm_hv.adapter.update()
        })
    }

    private fun getIcon(type: CardType): Int = when (type) {
        CardType.MASTER_CARD -> R.drawable.img_master_card
        CardType.VISA -> R.drawable.img_visa
        CardType.UNION_PAY -> R.drawable.img_union_pay
        CardType.DEFAULT -> R.drawable.img_union_pay//fixme
    }

    private fun setItemCurrency() {
        itemsCurrency.add(
            CurrencyItem(
                resources.getString(R.string.pounds),
                resources.getString(R.string.gbp),
                true
            )
        )
        itemsCurrency.add(
            CurrencyItem(
                resources.getString(R.string.euro),
                resources.getString(R.string.eur)
            )
        )
        itemsCurrency.add(
            CurrencyItem(
                resources.getString(R.string.rubles),
                resources.getString(R.string.rub)
            )
        )
    }

    override fun setSelectedColors(view: View) {
        view.icr_currency.setTextColor(resources.getColor(R.color.item))
        view.icr_symbol.setTextColor(resources.getColor(R.color.item))
        view.icr_ll.setBackgroundColor(resources.getColor(R.color.selected_currency))
    }

    override fun setDisableColors(view: View) {
        view.icr_currency.setTextColor(resources.getColor(R.color.text_color_grey))
        view.icr_symbol.setTextColor(resources.getColor(R.color.text_color_grey))
        view.icr_ll.setBackgroundColor(resources.getColor(R.color.item))
    }

}