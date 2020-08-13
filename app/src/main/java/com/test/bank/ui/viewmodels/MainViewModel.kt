package com.test.bank.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.bank.model.Card
import com.test.bank.repository.UserRepository
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel : ViewModel() {

    private val _card: MutableLiveData<Card> = MutableLiveData()

    val card: LiveData<Card>
        get() = _card

    init {
        updateCard()
    }

    private fun updateCard(){
        UserRepository.getCards().subscribeOn(Schedulers.io()).subscribe({ list ->
            list.let {
                list
                    .find { it.number == UserRepository.selectedCard }
                    ?.let {
                        _card.postValue(it)
                    }
                    ?: let {
                        UserRepository.selectedCard = list.first().number
                        _card.postValue(list.first())
                    }

//                UserRepository.selectedCard?.let {
//                    list.forEach {
//                    }
//                } ?: let {
//                    UserRepository.selectedCard = list.first().number
//                    _card.postValue(list.first())
//                }
            }
        }, {
            Log.e("ERROR", it.toString())
        })
    }

    fun changeCard() {
        updateCard()
    }

}