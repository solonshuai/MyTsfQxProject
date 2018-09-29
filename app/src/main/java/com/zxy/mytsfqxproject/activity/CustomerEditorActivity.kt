package com.zxy.mytsfqxproject.activity

import android.view.View
import android.widget.RadioGroup
import com.google.gson.JsonObject
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.Utils.StatusBarUtil
import com.zxy.mytsfqxproject.Utils.Tools
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.http.RetrofitManager
import com.zxy.mytsfqxproject.mvp.entity.ChooseCustomer
import kotlinx.android.synthetic.main.activity_customer_editor.*
import kotlinx.android.synthetic.main.top_view.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

/**
 * 客户的编辑和新增
 */
class CustomerEditorActivity : BaseActivity(), View.OnClickListener, RadioGroup.OnCheckedChangeListener, Callback<JsonObject> {
    override fun layoutId(): Int = R.layout.activity_customer_editor
    //是为编辑  否为添加
    var isEditor: Boolean = false
    private var sex: String? = null
    private var type: String? = null
    private var pamrms = HashMap<String, Any>()
    var item: ChooseCustomer.ResultBean.DataBean? = null
    override fun initView() {
        item = intent.getParcelableExtra("item")
        isEditor = intent.getBooleanExtra("isEditor", false)
        iv_left.setImageResource(R.mipmap.img_back)
        tv_right.text = "保存"
        if (isEditor) {
            tv_header_title.text = "修改客户"
        } else {
            tv_header_title.text = "新增客户"
        }
        tv_right.setOnClickListener(this)
        rg_sex.setOnCheckedChangeListener(this)
        rg_type.setOnCheckedChangeListener(this)
        if (item != null) {
            tv_kehu_name.setText(item!!.username.toString())
            et_phone.setText(item!!.phone)
            if (item!!.client_grade == 10) {
                et_customer_level.text = "A"
            } else if (item!!.client_grade == 20) {
                et_customer_level.text = "B"
            } else if (item!!.client_grade == 30) {
                et_customer_level.text = "C"
            }
            et_address.text = item!!.birthday
            if (item!!.sex == "男") {
                rb_nan1.isChecked = true
            } else {
                rb_nv1.isChecked = true
            }
            if (item!!.client_code == "个人") {
                rb_geren.isChecked = true
            } else {
                rb_danwei.isChecked = true
            }

        }
        this.let { StatusBarUtil.darkMode(it) }
        this.let { StatusBarUtil.setPaddingSmart(it, top_view) }
    }

    override fun start() {
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_right -> {
                if (sex == null && sex == "") {
                    showToast("请选择性别")
                    return
                }
                if (type == null && type == "") {
                    showToast("请选择类别")
                    return
                }
                pamrms["username"] = tv_kehu_name.text
                pamrms["phone"] = et_phone.text
                pamrms["sex"] = sex!!
                pamrms["client_type"] = type!!
                pamrms["birthday"] = et_birthday.text
                pamrms["client_from"] = "10"
                pamrms["address"] = et_address.text
                pamrms["client_grade"] = et_customer_level.text
                if (isEditor) {
                    pamrms["client_id"] = intent.getStringExtra("client_id")
                    RetrofitManager.service.editClient(Tools.getRequestBody(pamrms)).enqueue(this)
                } else {
                    RetrofitManager.service.addClient(Tools.getRequestBody(pamrms)).enqueue(this)
                }
            }
        }
    }

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
            showToast(errmsg)
        }
    }

    override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
        if (rb_nan1.id == checkedId) {
            sex = "男"
        }
        if (rb_nv1.id == checkedId) {
            sex = "女"
        }
        if (rb_geren.id == checkedId) {
            type = "个人"
        }
        if (rb_danwei.id == checkedId) {
            type = "单位"
        }
    }
}