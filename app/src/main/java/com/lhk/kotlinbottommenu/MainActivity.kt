package com.lhk.kotlinbottommenu

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.lhk.kotlinbottommenu.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mToolbar: Toolbar
    private lateinit var toolbar_title: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mToolbar = findViewById(R.id.toolbar)
        toolbar_title = findViewById(R.id.toolbar_title)
        toolbar_title.text = "首页"

        initTabLayout()
    }

    private fun initTabLayout() {
        var tabLayout = findViewById<TabLayout>(R.id.tabs)
        var viewPager = findViewById<ViewPager>(R.id.viewPager)
        setupViewPager(viewPager)
        viewPager.offscreenPageLimit = viewPager.adapter?.count ?: 0
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.tabMode = TabLayout.MODE_FIXED
    }

    private fun setupViewPager(viewPager: ViewPager){
        var vpAdapter = ViewPagerAdapter(supportFragmentManager)
        var newFragment = MainFragment()
        var data = Bundle()
        data.putInt("id",0)
        data.putString("title","每日推荐")
        newFragment.arguments = data
        vpAdapter.addFragment(newFragment,"每日推荐")

        newFragment = MainFragment()
        data = Bundle()
        data.putInt("id",1)
        data.putString("title","职场指南")
        newFragment.arguments = data
        vpAdapter.addFragment(newFragment,"职场指南")

        newFragment = MainFragment()
        data = Bundle()
        data.putInt("id",2)
        data.putString("title","经典语录")
        newFragment.arguments = data
        vpAdapter.addFragment(newFragment,"经典语录")

        viewPager.adapter = vpAdapter
    }

    class ViewPagerAdapter(fm: FragmentManager):FragmentPagerAdapter(fm){

        private val mFragmentList: MutableList<Fragment> = ArrayList()
        private val mFragmentTitleList: MutableList<String> = ArrayList()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }

        public fun addFragment(fragment: Fragment,title :String){
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }
    }
}
