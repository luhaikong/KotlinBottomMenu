package com.lhk.kotlinbottommenu.entity

data class User(
    val collectIds: ArrayList<Int>, val email: String,
    val icon: String, val id: Int,
    val password: String, val type: Int, val username: String?
)