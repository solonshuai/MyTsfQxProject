package com.zxy.mytsfqxproject.activity

import android.view.View
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), View.OnClickListener {
    override fun layoutId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        bt_login.setOnClickListener(this)
        tv_register.setOnClickListener(this)
        tv_forget.setOnClickListener(this)
    }

    override fun start() {}
    override fun onClick(v: View) {
        when (v.id) {
            R.id.bt_login -> {

            }
            R.id.tv_register -> {

            }
            R.id.tv_forget -> {

            }
        }
    }
}