package com.lhk.kotlinbottommenu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.lhk.kotlinbottommenu.adapter.GuideVpAdapter

class GuideActivity:AppCompatActivity() {

    private lateinit var mViewPager2: ViewPager2
    private var urls:MutableList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide)
        mViewPager2 = findViewById(R.id.viewpager2)
        mViewPager2.orientation = ViewPager2.ORIENTATION_VERTICAL

        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576756246874&di=7dfc9182541d6f0a3edeb7e0efcf6ba4&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201108%2F14%2F231546kmikn66dz13ddzba.jpg")
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576756246873&di=1cdbe7c872b7c2ea607ceab6d1eb6a2c&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn16%2F199%2Fw1000h799%2F20180516%2F6911-harvfht8692735.jpg")
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576756246873&di=13ef46a94768324f20782bdecd38f6fe&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2018-05-22%2F5b03837e8a008.jpg")
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576756246871&di=8f28bfddecaffe726c33e72a68fb916f&imgtype=0&src=http%3A%2F%2Fcdn.duitang.com%2Fuploads%2Fitem%2F201502%2F27%2F20150227072116_XnX4v.jpeg")
        mViewPager2.adapter = GuideVpAdapter(urls,this)
    }
}