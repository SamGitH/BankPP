package com.test.bank.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.bank.model.Card
import com.test.bank.repository.UserRepository
import io.reactivex.rxjava3.schedulers.Schedulers

class MyCardViewModel: ViewModel() {

    private val _cards: MutableLiveData<List<Card>> = MutableLiveData(listOf())

    val cards: LiveData<List<Card>>
        get() = _cards

    fun update(){
        UserRepository.getCards().subscribeOn(Schedulers.io()).subscribe({
            it.let {
                _cards.postValue(it)
            }
        }, {
            Log.e("ERROR", it.toString())
        })
    }
}