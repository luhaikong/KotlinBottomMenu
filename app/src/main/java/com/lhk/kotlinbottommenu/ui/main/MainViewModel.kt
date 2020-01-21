package com.lhk.kotlinbottommenu.ui.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lhk.kotlinbottommenu.entity.Subscriptions
import com.lhk.kotlinbottommenu.entity.WanResponse
import com.lhk.kotlinbottommenu.retrofit.ApiRepository
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {

    lateinit var mContext:Context
    var _list: MutableLiveData<MutableList<Subscriptions>>?=MutableLiveData<MutableList<Subscriptions>>()
    var list: MutableList<Subscriptions> ?= ArrayList<Subscriptions>()
        get() = field
        set(value) {
            field = value
        }

    public fun setContext(context: Context){
        this.mContext = context
    }

    public fun load(){
        GlobalScope.launch(Dispatchers.Main+ CoroutineName("wxarticle")) {
            val data = wxarticle()
            _list!!.value = data.data
            list = data.data
        }
    }

    public fun removeFirst(position:Int){
        list!!.removeAt(position)
        _list!!.value = list
    }

    private suspend fun wxarticle():WanResponse<MutableList<Subscriptions>>{
        return withContext(Dispatchers.IO) {
            ApiRepository(mContext).wxarticle()
        }
    }
}