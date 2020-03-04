package com.free.imagefilter

import android.app.ActivityManager
import android.content.Context
import android.os.Process
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

class FilterApplication : MultiDexApplication() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    companion object{
        private var context: Context? = null

        fun getContext(): Context? {
            return context
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        //只初始化一次 限主进程
        val processName = getProcessName(this, Process.myPid())
        if (null != processName) {
            if (processName != packageName) {
                return
            }
        }

    }

    /**
     * 获取app进程
     */
    fun getProcessName(context: Context, pid: Int): String? {
        val am =
            context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningApps =
            am.runningAppProcesses ?: return null
        for (procInfo in runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName
            }
        }
        return null
    }
}