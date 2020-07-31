package com.tan.toollibrary.tool

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo

object NetWorkTool {

    /**
     * 判断当前连接是否是wifi连接
     *
     * @param context
     * @return
     */
    fun isWifi(context: Context): Boolean {
        return getNetType(context) == ConnectivityManager.TYPE_WIFI
    }

    /**
     * 判断网络是否连接.
     * 需要权限<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"></uses-permission><br></br>
     *
     * @param context
     * @return - true 网络连接 - false 网络连接异常
     */
    @SuppressLint("MissingPermission")
    fun isConnected(context: Context): Boolean {
        try {
            val connectivity =context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivity != null) {
                val info = connectivity.activeNetworkInfo
                if (info != null && info.isAvailable && info.state == NetworkInfo.State.CONNECTED) {
                    return true
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    /**
     * 返回当前网络类型连接，如果没有网络，则返回-1
     *
     * @param context
     * @return int -返回网络类型 特殊:-1代表没有网络
     */
    private fun getNetType(context: Context): Int {
        val applicationContext = context.applicationContext
        return if (isConnected(applicationContext)) {
            val connMgr = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            @SuppressLint("MissingPermission")
            val info =connMgr.activeNetworkInfo
            info.type
        } else {
            -1
        }
    }

    /**
     * 打开网络设置界面
     *
     * @param activity
     */
    fun openNetworkSettingUi(activity: Activity) {
        val intent = Intent("/")
        val cn =
            ComponentName("com.android.settings", "com.android.settings.WirelessSettings")
        intent.component = cn
        intent.action = "android.intent.action.VIEW"
        activity.startActivityForResult(intent, 0)
    }
}