package com.zxy.mytsfqxproject.activity

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Build
import android.support.annotation.RequiresApi
import android.text.TextUtils
import android.view.View
import android.widget.RadioGroup
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.View.ShowWheelView
import com.zxy.mytsfqxproject.View.ShowWheelViewTime
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.mvp.Presenter.JdkDPresenter
import com.zxy.mytsfqxproject.mvp.contract.JdKdContract
import com.zxy.mytsfqxproject.mvp.entity.CarDetailBean
import kotlinx.android.synthetic.main.activity_jdkd.*
import kotlinx.android.synthetic.main.top_view.*
import java.util.*

/**
 * 接单开单
 */
class JDKDActivity : BaseActivity(), View.OnClickListener, RadioGroup.OnCheckedChangeListener, View.OnFocusChangeListener, JdKdContract.View {
    private val mJdkDPresenter by lazy { JdkDPresenter() }
    private var pamrms = HashMap<String, Any>()
    private val youBiaoList = ArrayList<String>().apply {
        add("空")
        add("少于1/4")
        add("1/4")
        add("1/2")
        add("3/4")
        add("满")
    }

    override fun layoutId(): Int = R.layout.activity_jdkd
    override fun initView() {
        mJdkDPresenter.attachView(this)
        mProgressDialog = ProgressDialog(this)
        iv_left.setImageResource(R.mipmap.img_back)
        iv_left.setOnClickListener { finish() }
        tv_header_title.text = "接待开单"
        iv_chepai_scanning.setOnClickListener(this)
        iv_chejia_scanning.setOnClickListener(this)
        tv_chexing.setOnClickListener(this)
        iv_phone_scanning.setOnClickListener(this)
        tv_youbiao.setOnClickListener(this)
        tv_jiaoche_time.setOnClickListener(this)
        tv_setting.setOnClickListener(this)
        tv_fuwu.setOnClickListener(this)
        tv_peijian.setOnClickListener(this)
        rg_choose.setOnCheckedChangeListener(this)
        rg_songxiuren.setOnCheckedChangeListener(this)
        et_chepai_num.onFocusChangeListener = this
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.iv_chepai_scanning -> {

            }
            R.id.iv_chejia_scanning -> {

            }
            R.id.tv_chexing -> {

            }
            R.id.iv_phone_scanning -> {

            }
            R.id.tv_youbiao -> {
                ShowWheelView.show(this, youBiaoList) { tv_youbiao.text = it }
            }
            R.id.tv_jiaoche_time -> {
                ShowWheelViewTime.show(this) { tv_jiaoche_time.text = it }
            }
            R.id.tv_setting -> {

            }
            R.id.tv_fuwu -> {

            }
            R.id.tv_peijian -> {

            }
        }
    }

    override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
        if (rb_nan1.id == checkedId) {

        }
        if (rb_nv1.id == checkedId) {

        }
        if (rb_nan2.id == checkedId) {

        }
        if (rb_nv2.id == checkedId) {

        }
    }

    override fun onFocusChange(v: View, hasFocus: Boolean) {
        if (hasFocus) {//此处为得到焦点时的处理内容
            et_chejia_num.isFocusable = true
        } else {
            if (TextUtils.isEmpty(et_chepai_num.text.toString())) return
            pamrms.clear()
            pamrms["car_number"] = et_chepai_num.text.toString()
            mJdkDPresenter.requestCarData(Tools.getRequestBody(pamrms))
        }
    }

    override fun start() {
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun setCarDetail(carDetailBean: CarDetailBean, isFocusable: Boolean) {
        et_chejia_num.setText(carDetailBean.result.car_vin)
        et_chejia_num.isFocusable = isFocusable
        tv_chexing.text = carDetailBean.result.car_brand_name + "  " + carDetailBean.result.car_series_name
        tv_kehu_name.setText(carDetailBean.result.username)
        tv_kehu_name.isFocusable = isFocusable
        et_songxiuren_name.setText(carDetailBean.result.username)
        et_songxiuren_name.isFocusable = isFocusable
        et_phone.setText(carDetailBean.result.phone)
        et_phone.isFocusable = isFocusable
        et_songxiuren_num.setText(carDetailBean.result.phone)
        et_songxiuren_num.isFocusable = isFocusable
        if (carDetailBean.result.sex == "男") {
            rb_nan1.isChecked = true
            rb_nan2.isChecked = true
        } else {
            rb_nv1.isChecked = true
            rb_nv2.isChecked = true
        }
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    override fun onDestroy() {
        super.onDestroy()
        mJdkDPresenter.detachView()
    }

}