package com.lhk.kotlinbottommenu

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.lhk.kotlinbottommenu.entity.Entity
import com.lhk.kotlinbottommenu.retrofit.ApiRepository
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.*

class LoginActivity : AppCompatActivity(){

    var userName:String = ""
    var userPassword:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_keyboard_arrow_left_black_24dp)
        toolbar.setNavigationOnClickListener(View.OnClickListener { onBackPressed() })

        btn_login.setOnClickListener(View.OnClickListener {
            doLogin(it)
        })
    }

    private fun doLogin(view:View){
        GlobalScope.launch(Dispatchers.Main+CoroutineName("login")) {
            val result = login(userName,userPassword)
            //更新ui
            if (result.data != null){
                btn_login.text = result.data.username
            } else {
                btn_login.text = result.errorMsg
            }

        }
    }

    private suspend fun login(name: String, password: String): Entity.WanResponse<Entity.User> {
        return withContext(Dispatchers.IO) {
            ApiRepository(this@LoginActivity).login(name, password)
        }
    }
}