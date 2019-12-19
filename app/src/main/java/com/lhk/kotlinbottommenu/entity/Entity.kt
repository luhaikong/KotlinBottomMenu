package com.lhk.kotlinbottommenu.entity

class Entity {
    data class WanResponse<out T>(val errorCode: Int,val errorMsg: String,val data: T?)

    data class User(val collectIds: List<Int>,val email: String,
                    val icon: String,val id: Int,
                    val password: String, val type: Int, val username: String?)
}