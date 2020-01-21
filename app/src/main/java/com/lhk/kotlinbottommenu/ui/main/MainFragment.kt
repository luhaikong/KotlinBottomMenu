package com.lhk.kotlinbottommenu.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lhk.kotlinbottommenu.R
import com.lhk.kotlinbottommenu.adapter.MainRvAdapter
import com.lhk.kotlinbottommenu.entity.Subscriptions

class MainFragment : Fragment(),MainRvAdapter.OnItemClickListener {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var mAdapter: MainRvAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var mRecyclerView: RecyclerView
    private var mList: MutableList<Subscriptions> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        mRecyclerView = root.findViewById(R.id.recyclerView)
        linearLayoutManager = LinearLayoutManager(this.requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        mAdapter = MainRvAdapter(mList, this.requireContext())
        mAdapter.onItemClickListener = this
        mRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        mRecyclerView.adapter = mAdapter
        mainViewModel.setContext(this.requireContext())
        mainViewModel._list!!.observe(this, Observer {
            mAdapter.list.clear()
            mAdapter.list.addAll(it)
            mAdapter.notifyDataSetChanged()
        })
        mainViewModel.load()

        return root
    }

    override fun onItemClick(subscriptions: Subscriptions, position: Int) {
        mainViewModel.removeFirst(position)
    }
}