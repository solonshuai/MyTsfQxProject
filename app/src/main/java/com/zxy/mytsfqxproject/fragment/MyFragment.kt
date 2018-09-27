package com.zxy.mytsfqxproject.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.activity.SetActivity
import com.zxy.mytsfqxproject.base.BaseFragment
import com.zxy.mytsfqxproject.db.SPUtil
import com.zxy.mytsfqxproject.http.UrlConstant
import kotlinx.android.synthetic.main.fragment_my.*
import kotlinx.android.synthetic.main.top_view.*

class MyFragment : BaseFragment(), View.OnClickListener {
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
        tv_header_title.text = mTitle
        iv_right.setImageResource(R.mipmap.img_set)
        iv_right.setOnClickListener(this)
        layout_chat.setOnClickListener(this)
        layout_help.setOnClickListener(this)
        layout_fankui.setOnClickListener(this)
        layout_share.setOnClickListener(this)
        val mRequestOptions = RequestOptions.circleCropTransform()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)//不做磁盘缓存
        Glide.with(this).load(SPUtil.getData(UrlConstant.userImg, "").toString()).apply(mRequestOptions).into(iv_icon)
        tv_username.text = SPUtil.getData(UrlConstant.userName, "").toString()
        //状态栏透明和间距处理
        activity?.let { StatusBarUtil.darkMode(it) }
        activity?.let { StatusBarUtil.setPaddingSmart(it, top_view) }
    }

    override fun lazyLoad() {
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.iv_right -> {
                startActivity(Intent(activity, SetActivity::class.java))
            }
            R.id.layout_chat -> {

            }
            R.id.layout_fankui -> {

            }
            R.id.layout_help -> {

            }
            R.id.layout_share -> {

            }
        }
    }
}