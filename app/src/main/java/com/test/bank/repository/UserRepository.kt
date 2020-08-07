package com.test.bank.repository

import com.test.bank.model.Card
import com.test.bank.model.CardType
import com.test.bank.model.Currency
import com.test.bank.model.History
import com.test.bank.network.NetworkService
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class UserRepository {

    fun getCards(): Observable<Card> {
        return NetworkService.userApi
            .getUser()
            .map { it.users }
            .toObservable()
            .flatMap { list ->
                Observable.fromIterable(list)
            }
            .map {
                Card(
                    it.card_number,
                    CardType.getCardType(it.type),
                    it.cardholder_name,
                    it.valid,
                    Currency(it.balance.toString(), "", "", ""),
                    it.transaction_history.map { model ->
                        History(
                            model.title,
                            model.icon_url,
                            model.date,
                            Currency(model.amount, "", "", "")
                        )
                    }
                )
            }
    }

    fun getFirstCard(): Single<Card> {
        return getCards().firstOrError()
    }


}