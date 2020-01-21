package com.lhk.kotlinbottommenu.retrofit

import com.lhk.kotlinbottommenu.entity.*
import retrofit2.http.*

interface ApiService {
    companion object {
        const val BASE_URL = "https://www.wanandroid.com"
    }

    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(@Field("username") username: String, @Field("password") password: String): WanResponse<User>

    @GET("/project/tree/json")
    suspend fun treeJson(): WanResponse<MutableList<TypeTree>>

    @GET("/project/list/1/json")
    suspend fun listJson(@Query("cid") cid:Int):WanResponse<TypeTreeListContent>

    @GET("/wxarticle/chapters/json")
    suspend fun wxarticle():WanResponse<MutableList<Subscriptions>>
}