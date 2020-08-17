package com.test.bank.repository

import com.test.bank.model.Currency
import com.test.bank.network.CurrencyNetworkService
import io.reactivex.rxjava3.core.Single

object CurrencyRepository {

    fun getCurrencies(): Single<Currency> {
        return CurrencyNetworkService.currencyApi.getCurrencies().map {
            getCurrencyToUsd(it.Valute.USD.Value, it.Valute.GBP.Value, it.Valute.EUR.Value)
        }
    }

    private fun getCurrencyToUsd(usd: Float, gbp: Float, eur: Float): Currency {
        return Currency(1.toFloat(), usd/eur, usd, usd/gbp)
    }
}