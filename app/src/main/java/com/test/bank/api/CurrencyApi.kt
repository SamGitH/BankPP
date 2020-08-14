package com.test.bank.api

import com.test.bank.api.model.CurrencyApiModel
import com.test.bank.api.model.UserApiResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface CurrencyApi {

    @GET("daily_json.js")
    fun getCurrencies(): Single<CurrencyApiModel>
}