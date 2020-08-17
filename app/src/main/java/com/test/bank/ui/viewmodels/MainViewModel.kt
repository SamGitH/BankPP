package com.test.bank.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.bank.model.Card
import com.test.bank.model.Currency
import com.test.bank.model.History
import com.test.bank.repository.CurrencyRepository
import com.test.bank.repository.UserRepository
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel : ViewModel() {

    private val _card: MutableLiveData<Card> = MutableLiveData()

    val card: LiveData<Card>
        get() = _card

    private var coeffCurrency = Currency(1.toFloat(), 0.toFloat(), 0.toFloat(), 0.toFloat())

    init {
        updateCard()
    }

    fun updateCard() {
        UserRepository.getCards().subscribeOn(Schedulers.io()).subscribe({ list ->
            list.let {
                list
                    .find { it.number == UserRepository.selectedCard }
                    ?.let {
                        _card.postValue(getCardWithCurrentCurrency(it))
                    }
                    ?: let {
                        UserRepository.selectedCard = list.first().number
                        _card.postValue(getCardWithCurrentCurrency(list.first()))
                    }
                setCurrentCurrency()
            }
        }, {
            Log.e("ERROR", it.toString())
        })
    }

    private fun setCurrentCurrency(){
        CurrencyRepository.getCurrencies().subscribeOn(Schedulers.io()).subscribe({ currency ->
            coeffCurrency = currency
            _card.value?.let {
                _card.postValue(getCardWithCurrentCurrency(it))
            }
        }, {
            Log.e("ERROR", it.toString())
        })
    }

    private fun getCardWithCurrentCurrency(card: Card): Card {
        val history = card.history.map {
            History(it.title, it.icon_url, it.date, getCurrentCurrency(it.amount.usd))
        }

        return Card(
            card.number,
            card.type,
            card.cardHolder,
            card.valid,
            getCurrentCurrency(card.balance.usd),
            history
        )
    }

    private fun getCurrentCurrency(balance: Float): Currency =
        Currency(
            balance,
            balance * coeffCurrency.eur,
            balance * coeffCurrency.rub,
            balance * coeffCurrency.gbp
        )
}