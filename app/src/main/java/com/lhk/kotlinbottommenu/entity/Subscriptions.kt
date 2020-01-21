package com.lhk.kotlinbottommenu.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Subscriptions(
    val children: List<String>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
):Parcelable