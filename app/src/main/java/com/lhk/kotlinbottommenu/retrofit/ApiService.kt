package com.lhk.kotlinbottommenu.retrofit

import com.lhk.kotlinbottommenu.entity.Entity
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    companion object {
        const val BASE_URL = "https://www.wanandroid.com"
    }

    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(@Field("username") username: String, @Field("password") password: String): Entity.WanResponse<Entity.User>
}