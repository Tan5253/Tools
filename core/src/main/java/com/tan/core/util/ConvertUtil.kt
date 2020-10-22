package com.tan.core.util

import android.text.TextUtils
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object ConvertUtil {
    /**
     * 对象转化为json对象
     *
     * @return
     */
    fun objectToJson(obj: Any?): String? {
        if (obj == null) {
            return ""
        }
        val mGoon: Gson
        try {
            mGoon = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            return mGoon.toJson(obj)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    /**
     * json转化为对象
     *
     * @return
     */
    fun <T> jsonToObject(jsonData: String?, clazz: Class<T>?): T? {
        if (TextUtils.isEmpty(jsonData)) {
            return null
        }
        var finalResult: T? = null
        try {
            val mGoon = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            finalResult = mGoon.fromJson(jsonData, clazz)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return finalResult
    }

    /**
     * json转化为集合
     */
    fun jsonToList(jsonData: String): List<*>? {
        if (TextUtils.isEmpty(jsonData)) {
            return null
        }
        var list: List<*>? = null
        try {
            val listType: Type = object : TypeToken<MutableList<*>?>() {}.type
            val mGoon = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            list = mGoon.fromJson<List<*>>(jsonData, listType)
        } catch (e: java.lang.Exception) {
        }
        return list
    }

    /**
     * json转化为Map
     */
    fun jsonToMap(jsonData: String): Map<*, *>? {
        if (TextUtils.isEmpty(jsonData)) {
            return null
        }
        var map: Map<*, *>? = null
        try {
            val listType: Type = object : TypeToken<Map<*, *>>() {}.type
            val mGoon = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            map = mGoon.fromJson<Map<*, *>>(jsonData, listType)
        } catch (e: java.lang.Exception) {
        }
        return map
    }
}