package com.test.bank.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.test.bank.R
import com.test.bank.model.Card
import com.test.bank.model.CardInfo
import com.test.bank.model.CardType
import com.test.bank.ui.adapters.CardsAdapter
import com.test.bank.ui.viewmodels.MyCardViewModel
import kotlinx.android.synthetic.main.fragment_my_cards.*

class MyCardFragment : Fragment(R.layout.fragment_my_cards), CardsAdapter.Callback {

    private val adapter: CardsAdapter by lazy {
        CardsAdapter(list)
    }

    private val viewModel: MyCardViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(MyCardViewModel::class.java)
    }

    private var list = arrayListOf<CardInfo>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter.unregisterCallback()
    }

    private fun bind() {
        fmc_rv.adapter = adapter
        adapter.registerCallback(this)

        viewModel.cards.observe(viewLifecycleOwner, Observer { cards ->
            list.clear()
            list.addAll(cards.map {
                CardInfo(
                    it.number,
                    it.cardHolder,
                    it.valid,
                    it.balance.usd,
                    "",
                    getIcon(it.type)
                )
            })
            adapter.update()
        })

        fmc_btn_back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun getIcon(type: CardType): Int = when (type) {
        CardType.MASTER_CARD -> R.drawable.img_master_card
        CardType.VISA -> R.drawable.img_visa
        CardType.UNION_PAY -> R.drawable.img_union_pay
        CardType.DEFAULT -> R.drawable.img_union_pay//fixme
    }

    override fun selectCard() {
        findNavController().popBackStack()
    }
}