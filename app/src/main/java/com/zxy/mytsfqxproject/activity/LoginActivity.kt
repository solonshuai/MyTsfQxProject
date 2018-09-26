package com.zxy.mytsfqxproject.activity

import android.content.Intent
import android.text.TextUtils
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
                if (TextUtils.isEmpty(et_name.text.toString())) {
                    showToast("请输入手机号码")
                    return
                }
                if (TextUtils.isEmpty(et_pwd.text.toString())) {
                    showToast("请输入密码")
                    return
                }
            }
            R.id.tv_register -> {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
            R.id.tv_forget -> {
                val intent = Intent(this, ForgetActivity::class.java)
                startActivity(intent)
            }
        }
    }
}