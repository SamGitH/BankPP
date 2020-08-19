package com.test.bank.network

import com.test.bank.api.CurrencyApi
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CurrencyNetworkService {

    private const val BASE_URL_CURRENCY = "https://www.cbr-xml-daily.ru/"

    val currencyApi = retrofitService()

    private fun retrofitService(): CurrencyApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_CURRENCY)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(CurrencyApi::class.java)
    }
}