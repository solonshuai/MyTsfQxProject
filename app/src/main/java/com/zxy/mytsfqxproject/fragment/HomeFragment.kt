package com.zxy.mytsfqxproject.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.youth.banner.BannerConfig
import com.youth.banner.loader.ImageLoader
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.View.GridDividerItemDecoration
import com.zxy.mytsfqxproject.activity.DDJSActivity
import com.zxy.mytsfqxproject.activity.FWDDActivity
import com.zxy.mytsfqxproject.activity.GCGLActivity
import com.zxy.mytsfqxproject.activity.JDKDActivity
import com.zxy.mytsfqxproject.adapter.MenuAdapter
import com.zxy.mytsfqxproject.base.BaseFragment
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.mvp.entity.MenuBean
import com.zxy.mytsfqxproject.recyclerView.OnRecyclerItemClickListener
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.top_view.*
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class HomeFragment : BaseFragment(), View.OnClickListener, OnRecyclerItemClickListener<MenuBean.ResultBean>, Callback<MenuBean> {
    private var mTitle: String? = null
    private val imgs = ArrayList<String>().apply {
        add("http://ww3.sinaimg.cn/large/610dc034jw1fasakfvqe1j20u00mhgn2.jpg")
        add("http://h.hiphotos.baidu.com/image/pic/item/09fa513d269759eea254ddbabefb43166d22dfbf.jpg")
        add("http://f.hiphotos.baidu.com/image/pic/item/8694a4c27d1ed21bfb91c832a66eddc450da3f89.jpg")
    }
    private lateinit var menuAdapter: MenuAdapter
    private var pamrms = HashMap<String, Any>()
    @SuppressLint("HandlerLeak")
    var handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                0 -> {

                }
            }
        }
    }

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

    @SuppressLint("ResourceAsColor")
    override fun initView(view: View) {
        iv_left.setImageResource(R.mipmap.img_customer)
        iv_right.setImageResource(R.mipmap.img_order)
        iv_left.setOnClickListener(this)
        iv_right.setOnClickListener(this)
        tv_header_title.text = mTitle
        mBanner.setImages(imgs)//设置图片源
        mBanner.setImageLoader(object : ImageLoader() {
            override fun displayImage(context: Context, path: Any, imageView: ImageView) {
                Glide.with(activity).load(path as String)
                        .into(imageView)
            }
        })
        mBanner.start()
        menuAdapter = MenuAdapter((this.activity as Context?)!!)
        menuAdapter.onRecyclerItemClickListener = this
        mRecyclerView.layoutManager = GridLayoutManager(this.activity!!, 3) as RecyclerView.LayoutManager?
        mRecyclerView.addItemDecoration(GridDividerItemDecoration(1, R.color.color_gray))
        mRecyclerView.adapter = menuAdapter
        SmartRefreshLayout.refreshHeader = ClassicsHeader(this.activity).setSpinnerStyle(SpinnerStyle.Scale)
        SmartRefreshLayout.refreshFooter = ClassicsFooter(this.activity).setSpinnerStyle(SpinnerStyle.Scale)
        SmartRefreshLayout.isEnableLoadmore = false
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
        pamrms["class"] = "home"
        RetrofitManager.service.getMenu(Tools.getRequestBody(pamrms)).enqueue(this)
    }

    override fun onFailure(call: Call<MenuBean>, t: Throwable) {
        showToast(getString(R.string.http_error))
    }

    override fun onResponse(call: Call<MenuBean>, response: Response<MenuBean>) {
        if (response.body()!!.code == 200) {
            menuAdapter.setDataList(response.body()!!.result)
        } else {
            showToast(response.body()!!.errmsg)
        }
    }

    override fun onItemClick(v: View, item: MenuBean.ResultBean, position: Int) {
        when (item.alias) {
            "JDKD" -> {
                startActivity(Intent(activity, JDKDActivity::class.java))
            }
            "FWDD" -> {
                startActivity(Intent(activity, FWDDActivity::class.java))
            }
            "GCGL" -> {
                startActivity(Intent(activity, GCGLActivity::class.java))
            }
            "DDJS" -> {
                startActivity(Intent(activity, DDJSActivity::class.java))
            }
        }
    }

}