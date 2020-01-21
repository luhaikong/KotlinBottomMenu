package com.lhk.kotlinbottommenu.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lhk.kotlinbottommenu.R
import com.lhk.kotlinbottommenu.adapter.HomeRvAdapter
import com.lhk.kotlinbottommenu.entity.Entity
import com.lhk.kotlinbottommenu.retrofit.ApiRepository
import kotlinx.coroutines.*

class HomeFragment : Fragment() {

    private lateinit var mAdapter: HomeRvAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var mRecyclerView: RecyclerView
    private var mList: MutableList<Entity.TypeTree<Any>> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        mRecyclerView = root.findViewById(R.id.recyclerView)
        linearLayoutManager = LinearLayoutManager(this.requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        mAdapter = HomeRvAdapter(mList, this.requireContext())
        mRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        mRecyclerView.adapter = mAdapter

        doTreeJson()

        return root
    }

    private fun doTreeJson(){
        GlobalScope.launch(Dispatchers.Main+ CoroutineName("treeJson")){
            val result = treeJson()
            mAdapter.list.clear()
            mAdapter.list.addAll(result.data!!)
            mAdapter.notifyDataSetChanged()
        }
    }

    private suspend fun treeJson():Entity.WanResponse<MutableList<Entity.TypeTree<Any>>>{
        return withContext(Dispatchers.IO){
            ApiRepository(this@HomeFragment.requireContext()).treeJson()
        }
    }
}