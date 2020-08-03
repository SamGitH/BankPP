package com.test.bank

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_my_cards.*

class MyCardFragment: Fragment(R.layout.fragment_my_cards) {

    private val adapter: CardsAdapter by lazy {
        CardsAdapter(list)
    }

    var list = arrayListOf<Card>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.add(Card("1234 1234 1234 1234", ""))
        list.add(Card("3244 1234 4234 1234", ""))
        list.add(Card("3244 2434 4234 1234", ""))
        list.add(Card("3244 4324 4234 1234", ""))

        fmc_rv.adapter = adapter

     }

    override fun onStart() {
        super.onStart()
        adapter.update()
    }

}