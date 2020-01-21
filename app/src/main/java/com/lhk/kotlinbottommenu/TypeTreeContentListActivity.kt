package com.lhk.kotlinbottommenu

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lhk.kotlinbottommenu.adapter.TypeTreeContentRvAdapter
import com.lhk.kotlinbottommenu.entity.Data
import com.lhk.kotlinbottommenu.entity.TypeTree
import com.lhk.kotlinbottommenu.entity.TypeTreeListContent
import com.lhk.kotlinbottommenu.entity.WanResponse
import com.lhk.kotlinbottommenu.retrofit.ApiRepository
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.*

class TypeTreeContentListActivity : AppCompatActivity(){

    lateinit var mTypeTree: TypeTree

    private lateinit var mContext: Context
    private lateinit var mAdapter: TypeTreeContentRvAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var mRecyclerView: RecyclerView
    private var mList: MutableList<Data> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type_tree_list)
        setSupportActionBar(toolbar)
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_keyboard_arrow_left_black_24dp)
        toolbar.setNavigationOnClickListener(View.OnClickListener { onBackPressed() })
        mContext = this

        mTypeTree = intent.getParcelableExtra("EXTRA")

        mRecyclerView = findViewById(R.id.recyclerView)
        linearLayoutManager = LinearLayoutManager(mContext)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        mAdapter = TypeTreeContentRvAdapter(mList, mContext)
        mAdapter.onItemClickListener = OnTypeTreeClickListener(mContext)
        mRecyclerView.layoutManager = LinearLayoutManager(mContext)
        mRecyclerView.adapter = mAdapter

        doListJson(mTypeTree.id)
    }

    private fun doListJson(cid: Int){
        GlobalScope.launch(Dispatchers.Main+CoroutineName("listJson")) {
            val result = listJson(cid)
            //更新ui
            mAdapter.list.clear()
            mAdapter.list.addAll(result.data!!.datas)
            mAdapter.notifyDataSetChanged()
        }
    }

    private suspend fun listJson(cid: Int): WanResponse<TypeTreeListContent> {
        return withContext(Dispatchers.IO) {
            ApiRepository(this@TypeTreeContentListActivity).listJson(cid)
        }
    }

    class OnTypeTreeClickListener(val context: Context):TypeTreeContentRvAdapter.OnItemClickListener{
        override fun onItemClick(data: Data, position: Int) {
            Toast.makeText(context,data.desc,Toast.LENGTH_LONG).show()
        }
    }
}