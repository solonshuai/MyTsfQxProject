package com.zxy.mytsfqxproject

import android.content.Context
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.zxy.mytsfqxproject.db.SPUtil
import kotlin.properties.Delegates

/**
 * 单列的Application
 */
class MyApplication : MultiDexApplication() {

    companion object {
        var context: Context by Delegates.notNull()
            private set
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        context=applicationContext
        SPUtil.getInstance(this)
    }
}