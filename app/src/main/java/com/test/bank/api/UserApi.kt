package com.test.bank.api

import com.test.bank.api.model.UserApiResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface UserApi {

    @GET("test/android/v1/users.json")
    fun getUser(): Single <UserApiResponse>
}