package com.lhk.kotlinbottommenu.demo

import android.util.Log

abstract class Language {
    val TAG = this.javaClass.simpleName  // 自身的属性

    abstract var name : String           // 抽象属性

    abstract fun learn()

    fun speak(name:String){
        Log.e(TAG,"我讲的是$name")
    }
}