package com.test.bank.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.bank.model.Card
import com.test.bank.repository.UserRepository
import io.reactivex.rxjava3.schedulers.Schedulers

class MyCardViewModel: ViewModel() {

    private val _card: MutableLiveData<List<Card>> = MutableLiveData(listOf())

    val card: LiveData<List<Card>>
        get() = _card

    private val userRepository: UserRepository by lazy {
        UserRepository()
    }

    init {
        userRepository.getCards().subscribeOn(Schedulers.io()).subscribe({
            val list = mutableListOf<Card>()
            list.addAll(_card.value!!)
            list.add(it)
            _card.postValue(list)
        }, {
            Log.e("ERROR", it.toString())
        })
    }
}