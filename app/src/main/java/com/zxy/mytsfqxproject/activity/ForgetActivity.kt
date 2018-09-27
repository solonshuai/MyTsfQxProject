package com.zxy.mytsfqxproject.activity

import android.text.TextUtils
import android.view.View
import com.google.gson.JsonObject
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.http.RetrofitManager
import kotlinx.android.synthetic.main.activity_forget.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgetActivity : BaseActivity(), View.OnClickListener {
    override fun layoutId(): Int = R.layout.activity_forget

    override fun initView() {
        bt_login.setOnClickListener(this)
        tv_getCode.setOnClickListener(this)
    }

    override fun start() {
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_getCode -> {
                RetrofitManager.service.yanSmsCode(et_name.text.toString(), "find", et_code.text.toString()).enqueue(object : Callback<JsonObject> {
                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        showToast(getString(R.string.http_error))
                    }

                    override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                        val sss = response.body()!!.toString()
                        val jobj = JSONObject(sss)
                        val errmsg = jobj.get("errmsg").toString()
                        val code = jobj.get("code")
                        if (code == 200) {
                            showToast(errmsg)
                        } else {
                            showToast(getString(R.string.http_error))
                        }
                    }
                })
            }
            R.id.bt_login -> {
                if (TextUtils.isEmpty(et_name.text.toString())) {
                    showToast("请输入手机号码")
                    return
                }
                if (TextUtils.isEmpty(et_code.text.toString())) {
                    showToast("请输入验证码")
                    return
                }
                if (TextUtils.isEmpty(et_pwd.text.toString())) {
                    showToast("请输入密码")
                    return
                }
                if (TextUtils.isEmpty(et_pwd2.text.toString())) {
                    showToast("请输入密码")
                    return
                }
                if (et_pwd.text.toString() != et_pwd2.text.toString()) {
                    showToast("两次密码不一致")
                    return
                }
                RetrofitManager.service.findLoginPwd(et_name.text.toString(), et_code.text.toString(), et_pwd.text.toString(), et_pwd2.text.toString()).enqueue(object : Callback<JsonObject> {
                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        showToast(getString(R.string.http_error))
                    }

                    override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                        val sss = response.body()!!.toString()
                        val jobj = JSONObject(sss)
                        val errmsg = jobj.get("errmsg").toString()
                        val code = jobj.get("code")
                        if (code == 200) {
                            showToast(errmsg)
                            finish()
                        } else {
                            showToast(getString(R.string.http_error))
                        }
                    }
                })
            }

        }
    }

}