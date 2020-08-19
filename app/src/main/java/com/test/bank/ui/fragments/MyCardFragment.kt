package com.test.bank.ui.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.test.bank.R
import com.test.bank.model.CardInfo
import com.test.bank.model.CardType
import com.test.bank.ui.adapters.CardsAdapter
import com.test.bank.ui.viewmodels.MyCardViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_my_cards.*

class MyCardFragment : Fragment(R.layout.fragment_my_cards), CardsAdapter.Callback {

    private val adapter: CardsAdapter by lazy {
        CardsAdapter(list)
    }

    private val viewModel: MyCardViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(MyCardViewModel::class.java)
    }

    private var list = arrayListOf<CardInfo>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        checkNetwork()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fmc_progress_layout.visibility = View.VISIBLE
        bind()
        viewModel.update()
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter.unregisterCallback()
    }

    private fun bind() {
        fmc_rv.adapter = adapter
        adapter.registerCallback(this)

        viewModel.cards.observe(viewLifecycleOwner, Observer { cards ->
            fmc_progress_layout.visibility = View.INVISIBLE
            list.clear()
            list.addAll(cards.map {
                CardInfo(
                    it.number,
                    it.cardHolder,
                    it.valid,
                    it.balance.usd.toString(),
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
        CardType.DEFAULT -> R.drawable.img_card_not_found
    }

    private fun checkNetwork() {
        if (!isNetworkConnected()){
            findNavController().navigate(R.id.noInternetFragment)
        }
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    override fun selectCard() {
        findNavController().popBackStack()
    }
}