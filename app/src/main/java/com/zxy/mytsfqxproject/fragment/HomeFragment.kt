package com.zxy.mytsfqxproject.fragment

import android.os.Bundle
import android.view.View
import com.zxy.mytsfqxproject.base.BaseFragment

class HomeFragment : BaseFragment() {
    private var mTitle: String? = null

    companion object {
        fun getInstance(title: String): HomeFragment {
            val fragment = HomeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }
    override fun getLayoutId(): Int {
        return 0
    }

    override fun initView(view: View) {
    }

    override fun lazyLoad() {
    }
}