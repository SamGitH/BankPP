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
//    private val _history: MutableLiveData<List<History>> = MutableLiveData()

    val card: LiveData<Card>
        get() = _card

//    val history: LiveData<List<History>>
//        get() = _history

    private val userRepository: UserRepository by lazy {
        UserRepository()
    }

    init {
        userRepository.getFirstCard().subscribeOn(Schedulers.io()).subscribe({
            _card.postValue(it)
        }, {
            Log.e("ERROR", it.toString())
        })
    }

    fun changeCard(card: Card){
        _card.postValue(card)
    }

}