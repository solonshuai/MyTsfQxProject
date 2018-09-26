package com.zxy.mytsfqxproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.zxy.mytsfqxproject.View.TabEntity
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.fragment.HomeFragment
import com.zxy.mytsfqxproject.fragment.MyFragment
import com.zxy.mytsfqxproject.fragment.NewsFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : BaseActivity() {
    private val mTitles = arrayOf("首页", "消息", "我的")

    // 未被选中的图标
    private val mIconUnSelectIds = intArrayOf(R.mipmap.ic_home_normal, R.mipmap.ic_news_normal, R.mipmap.ic_my_normal)
    // 被选中的图标
    private val mIconSelectIds = intArrayOf(R.mipmap.ic_home_selected, R.mipmap.ic_news_select, R.mipmap.ic_my_selecte)

    private val mTabEntities = ArrayList<CustomTabEntity>()

    private var mHomeFragment: HomeFragment? = null
    private var mNewsFragment: NewsFragment? = null
    private var mMyFragment: MyFragment? = null

    //默认为0
    private var mIndex = 0


    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            mIndex = savedInstanceState.getInt("currTabIndex")
        }
        super.onCreate(savedInstanceState)
        initTab()
        tab_layout.currentTab = mIndex
        switchFragment(mIndex)
    }

    //初始化底部菜单
    private fun initTab() {
        (0 until mTitles.size)
                .mapTo(mTabEntities) { TabEntity(mTitles[it], mIconSelectIds[it], mIconUnSelectIds[it]) }
        //为Tab赋值
        tab_layout.setTabData(mTabEntities)
        tab_layout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                //切换Fragment
                switchFragment(position)
            }

            override fun onTabReselect(position: Int) {

            }
        })
    }

    /**
     * 切换Fragment
     * @param position 下标
     */
    private fun switchFragment(position: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (position) {
            0 // 首页
            -> mHomeFragment?.let {
                transaction.show(it)
            } ?: HomeFragment.getInstance(mTitles[position]).let {
                mHomeFragment = it
                transaction.add(R.id.fl_container, it, "home")
            }
            1
            -> mNewsFragment?.let {
                transaction.show(it)
            } ?: NewsFragment.getInstance(mTitles[position]).let {
                mNewsFragment = it
                transaction.add(R.id.fl_container, it, "discovery")
            }
            2  //热门
            -> mMyFragment?.let {
                transaction.show(it)
            } ?: MyFragment.getInstance(mTitles[position]).let {
                mMyFragment = it
                transaction.add(R.id.fl_container, it, "hot")
            }
        }

        mIndex = position
        tab_layout.currentTab = mIndex
        transaction.commitAllowingStateLoss()
    }


    /**
     * 隐藏所有的Fragment
     * @param transaction transaction
     */
    private fun hideFragments(transaction: FragmentTransaction) {
        mHomeFragment?.let { transaction.hide(it) }
        mNewsFragment?.let { transaction.hide(it) }
        mMyFragment?.let { transaction.hide(it) }
    }


    @SuppressLint("MissingSuperCall")
    override fun onSaveInstanceState(outState: Bundle) {
        //记录fragment的位置,防止崩溃 activity被系统回收时，fragment错乱
        if (tab_layout != null) {
            outState.putInt("currTabIndex", mIndex)
        }
    }

    override fun initView() {
    }

    override fun start() {
    }
}
