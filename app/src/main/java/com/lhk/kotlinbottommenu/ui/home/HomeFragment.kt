package com.lhk.kotlinbottommenu.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lhk.kotlinbottommenu.R
import com.lhk.kotlinbottommenu.TypeTreeContentListActivity
import com.lhk.kotlinbottommenu.adapter.HomeRvAdapter
import com.lhk.kotlinbottommenu.entity.TypeTree
import com.lhk.kotlinbottommenu.entity.WanResponse
import com.lhk.kotlinbottommenu.retrofit.ApiRepository
import kotlinx.coroutines.*

class HomeFragment : Fragment() {

    private lateinit var mContext:Context
    private lateinit var mAdapter: HomeRvAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var mRecyclerView: RecyclerView
    private var mList: MutableList<TypeTree> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        mContext = this.requireContext()
        mRecyclerView = root.findViewById(R.id.recyclerView)
        linearLayoutManager = LinearLayoutManager(this.requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        mAdapter = HomeRvAdapter(mList, this.requireContext())
        mAdapter.onItemClickListener = OnTypeTreeClickListener(mContext)
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

    private suspend fun treeJson(): WanResponse<MutableList<TypeTree>> {
        return withContext(Dispatchers.IO){
            ApiRepository(mContext).treeJson()
        }
    }

    class OnTypeTreeClickListener(val context: Context):HomeRvAdapter.OnItemClickListener{
        override fun onItemClick(typeTree: TypeTree, position: Int) {
            val intent = Intent(context,TypeTreeContentListActivity::class.java)
            intent.putExtra("EXTRA",typeTree)
            context.startActivity(intent)
        }
    }
}