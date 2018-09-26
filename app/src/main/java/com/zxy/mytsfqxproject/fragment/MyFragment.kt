package com.zxy.mytsfqxproject.fragment

import android.os.Bundle
import android.view.View
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_my.*

class MyFragment : BaseFragment() {
    private var mTitle: String? = null

    companion object {
        fun getInstance(title: String): MyFragment {
            val fragment = MyFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_my
    }

    override fun initView(view: View) {
        activity?.let { StatusBarUtil.setPaddingSmart(it, top_view) }
    }

    override fun lazyLoad() {
    }
}