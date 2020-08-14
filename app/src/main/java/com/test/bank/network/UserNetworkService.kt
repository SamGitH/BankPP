package com.test.bank.network

import com.test.bank.api.UserApi
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserNetworkService {

    private const val BASE_URL_USER = "https://hr.peterpartner.net/"

    val userApi = retrofitService()

    private fun retrofitService(): UserApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_USER)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(UserApi::class.java)
    }
}