package com.zxy.mytsfqxproject.activity

import android.support.v7.widget.LinearLayoutManager
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.adapter.ChooseCustomerAdapter
import com.zxy.mytsfqxproject.base.BaseActivity
import kotlinx.android.synthetic.main.activity_choose_customer.*
import kotlinx.android.synthetic.main.top_view.*

class ChooseCustomerActivity : BaseActivity() {
    override fun layoutId(): Int = R.layout.activity_choose_customer
    private lateinit var mChooseCustomerAdapter: ChooseCustomerAdapter
    var pageIndex = 0
    val totalPages = 0
    override fun initView() {
        iv_left.setImageResource(R.mipmap.img_back)
        iv_left.setOnClickListener { finish() }
        tv_header_title.text = "选择客户"
        mChooseCustomerAdapter = ChooseCustomerAdapter(this)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = mChooseCustomerAdapter
        mSmartRefreshLayout.refreshHeader = ClassicsHeader(this).setSpinnerStyle(SpinnerStyle.Scale)
        mSmartRefreshLayout.refreshFooter = ClassicsFooter(this).setSpinnerStyle(SpinnerStyle.Scale)
        mSmartRefreshLayout.isEnableLoadmore = false
        mSmartRefreshLayout.setOnRefreshListener { refreshlayout ->
            mSmartRefreshLayout.isEnableLoadmore = true
            refreshlayout.finishRefresh(1000)
            start()
        }
        mSmartRefreshLayout.setOnLoadmoreListener { refreshlayout ->
            if (pageIndex >= totalPages) {
                mSmartRefreshLayout.finishLoadmore()
                showToast("没有更多了")
            } else {
                pageIndex += 1
                refreshlayout.finishLoadmore(1000)
                start()
            }
        }
    }

    override fun start() {

    }
}