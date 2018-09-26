package com.zxy.mytsfqxproject.activity

import android.Manifest
import android.content.Intent
import android.text.TextUtils
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.zxy.mytsfqxproject.MainActivity
import com.zxy.mytsfqxproject.R
import com.zxy.mytsfqxproject.base.BaseActivity
import com.zxy.mytsfqxproject.db.SPUtil
import com.zxy.mytsfqxproject.http.UrlConstant
import kotlinx.android.synthetic.main.activity_splash.*
import pub.devrel.easypermissions.EasyPermissions


/**
 * Created by xuhao on 2017/12/5.
 * desc: 启动页
 */

class SplashActivity : BaseActivity() {

    private var alphaAnimation: AlphaAnimation? = null
    override fun layoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
        //渐变展示启动屏
        alphaAnimation = AlphaAnimation(0.3f, 1.0f)
        alphaAnimation?.duration = 3000L
        alphaAnimation?.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(arg0: Animation) {
                redirectTo()
            }

            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationStart(animation: Animation) {}

        })
        checkPermission()
    }

    fun redirectTo() {
        if (TextUtils.isEmpty(SPUtil.getData(UrlConstant.token, "").toString())) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        finish()
    }

    override fun start() {
    }

    /**
     * 6.0以下版本(系统自动申请) 不会弹框
     * 有些厂商修改了6.0系统申请机制，他们修改成系统自动申请权限了
     */
    private fun checkPermission() {
        val perms = arrayOf(Manifest.permission.READ_PHONE_STATE)
        EasyPermissions.requestPermissions(this, "应用需要以下权限，请允许", 0, *perms)
    }


    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        if (requestCode == 0) {
            if (perms.isNotEmpty()) {
                if (perms.contains(Manifest.permission.READ_PHONE_STATE)) {
                    if (alphaAnimation != null) {
                        layout_splash.startAnimation(alphaAnimation)
                    }
                }
            }
        }
    }
}