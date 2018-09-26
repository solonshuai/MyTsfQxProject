package com.zxy.mytsfqxproject.fragment

import android.os.Bundle
import android.view.View
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.top_view.*

class HomeFragment : BaseFragment(), View.OnClickListener {
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
        return R.layout.fragment_home
    }

    override fun initView(view: View) {
        iv_left.setImageResource(R.mipmap.img_customer)
        iv_right.setImageResource(R.mipmap.img_order)
        iv_left.setOnClickListener(this)
        iv_right.setOnClickListener(this)
        tv_header_title.text = mTitle
        //状态栏透明和间距处理
        activity?.let { StatusBarUtil.darkMode(it) }
        activity?.let { StatusBarUtil.setPaddingSmart(it, top_view) }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.iv_left -> {

            }
            R.id.iv_right -> {

            }

        }
    }

    override fun lazyLoad() {
    }
}