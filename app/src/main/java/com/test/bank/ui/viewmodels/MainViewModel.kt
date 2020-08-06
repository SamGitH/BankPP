package com.test.bank.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.bank.model.Card
import com.test.bank.model.History

class MainViewModel: ViewModel() {

    var card: MutableLiveData<Card> = MutableLiveData()
    var history: MutableLiveData<List<History>> = MutableLiveData()

    init {
        card//get
    }
}