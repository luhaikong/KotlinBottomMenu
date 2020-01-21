package com.lhk.kotlinbottommenu.demo

import android.util.Log

class Java : Language() {

    override var name: String = ""
        get() = field

    override fun learn() {
        name = "JAVA"
        Log.e(TAG,"我学习的是$name")
    }
}