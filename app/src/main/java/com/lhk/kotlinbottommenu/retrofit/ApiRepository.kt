package com.lhk.kotlinbottommenu.retrofit

import com.lhk.kotlinbottommenu.entity.Entity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRepository {
    val retrofit = Retrofit.Builder()
        .baseUrl(ApiService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    suspend fun login(name: String,password: String): Entity.WanResponse<Entity.User> {
        return retrofit.login(name,password)
    }
}