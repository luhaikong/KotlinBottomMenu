package com.lhk.kotlinbottommenu.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TypeTree(
    val children: ArrayList<String>?, val courseId: Int, val id: Int,
    val name: String, val order: Int, val parentChapterId: Int,
    val visible: Int
) : Parcelable