package com.tan.toollibrary.tool

import android.content.Context
import android.content.Intent
import android.net.Uri
import java.util.*

/**
 * 常用工具
 */
object CommonTool {

    /**
     * 生成随机数
     */
    fun getRandomNum(max: Int): Int {
        val r = Random()
        return r.nextInt(max)
    }

    /**
     * -- 拨打电话 --
     */
    fun callPhone(context: Context, phone: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        val data = Uri.parse("tel:$phone")
        intent.data = data
        context.startActivity(intent)
    }

    /**
     * 判断集合是否为空
     */
    fun isListEmpty(list: List<*>?): Boolean {
        return !(list != null && list.isNotEmpty())
    }

    /**
     * 字符串是否为空
     */
    fun isEmpty(input: String?): Boolean {
        if (input == null || "" == input || "null" == input) return true
        for (c in input) {
           // val c = element
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false
            }
        }
        return true
    }

    /**
     * 处理字符串null
     * @param value
     * @return
     */
    fun emptyValue(value: String?): String? {
        return if (isEmpty(value)) { "" } else value
    }

    /**
     * 防止双击
     */
    private var lastClickTime: Long = 0
    private const val TIME: Long = 800

    fun isFastDoubleClick(): Boolean {
        val time = System.currentTimeMillis()
        if (time - lastClickTime < TIME) {
            return true
        }
        lastClickTime = time
        return false
    }
}