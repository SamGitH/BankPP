package com.test.bank.repository

import com.test.bank.model.Currency
import com.test.bank.network.CurrencyNetworkService
import io.reactivex.rxjava3.core.Single

object CurrencyRepository {
     fun getCurrencies(): Single<Currency> {
         return CurrencyNetworkService.currencyApi.getCurrencies().map {
//             it.valute.find { list1 ->
//                 list1.find { list2 ->
//                     list2.charCode == "GBP"
//                 }
//             }
             val currency = Currency("", "", "", "")
             it.valute.forEach { list1 ->
                 list1.forEach { list2 ->
                     when (list2.charCode){
                         "GBP" -> currency.gbp = list2.value.toString()
                         "USD" -> currency.usd = list2.value.toString()
                         "EUR" -> currency.eur = list2.value.toString()
                     }
                 }
             }
             return@map currency
         }
     }
}