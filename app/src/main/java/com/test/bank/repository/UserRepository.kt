package com.test.bank.repository

import com.test.bank.model.Card
import com.test.bank.model.CardType
import com.test.bank.model.Currency
import com.test.bank.model.History
import com.test.bank.network.UserNetworkService
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

object UserRepository {

    var selectedCard: String? = null

    fun getCards(): Single<List<Card>> {
        return UserNetworkService.userApi
            .getUser()
            .map {
                it.users
            }
            .toObservable()
            .flatMap {
                Observable.fromIterable(it)
            }
            .map {
                Card(
                    it.card_number,
                    CardType.getCardType(it.type),
                    it.cardholder_name,
                    it.valid,
                    Currency(it.balance, 0.toFloat(), 0.toFloat(), 0.toFloat()),
                    it.transaction_history.map { model ->
                        History(
                            model.title,
                            model.icon_url,
                            model.date,
                            Currency(model.amount.toFloat(), 0.toFloat(), 0.toFloat(), 0.toFloat())
                        )
                    }
                )
            }
            .toList()
    }
}