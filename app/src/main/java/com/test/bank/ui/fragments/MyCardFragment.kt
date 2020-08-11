package com.test.bank.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.test.bank.model.Card
import com.test.bank.R
import com.test.bank.model.CardInfo
import com.test.bank.ui.adapters.CardsAdapter
import kotlinx.android.synthetic.main.fragment_my_cards.*

class MyCardFragment: Fragment(R.layout.fragment_my_cards) {

    private val adapter: CardsAdapter by lazy {
        CardsAdapter(list)
    }

    var list = arrayListOf<CardInfo>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
     }

    override fun onStart() {
        super.onStart()
        adapter.update()
    }

    private fun bind(){
        fmc_rv.adapter = adapter

        fmc_btn_back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}