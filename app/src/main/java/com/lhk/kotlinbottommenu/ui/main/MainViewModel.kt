package com.lhk.kotlinbottommenu.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _list = MutableLiveData<MutableList<String>>().apply {
        var data = arrayListOf("0")
        for (i in 1..100) {
            data.add(i.toString())
        }
        value = data
    }

    val list: LiveData<MutableList<String>> = _list
}