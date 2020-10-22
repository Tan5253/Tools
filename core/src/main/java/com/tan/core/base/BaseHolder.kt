package com.tan.core.base

import android.util.SparseArray
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView


class BaseHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var mViewArr: SparseArray<View?>? = null

    /**
     * 所有添加了点击事件的控件的id集
     */
    private var childClickIdS: LinkedHashSet<Int?>? = null

    /**
     * 所有添加了长按事件的控件的id集
     */
    private var childLongClickIdS: LinkedHashSet<Int?>? = null

    private var adapter: BaseAdapter<*>? = null

    init {
        mViewArr = SparseArray()
        childClickIdS = LinkedHashSet()
        childLongClickIdS = LinkedHashSet()
    }

    fun <T : View?> getView(@IdRes viewId: Int): T {
        if (mViewArr!!.get(viewId) == null) {
            mViewArr!!.put(viewId, itemView.findViewById(viewId))
        }
        return mViewArr!!.get(viewId) as T
    }

    fun setText(@IdRes viewId: Int, text: CharSequence): BaseHolder {
        val tv: TextView = getView(viewId)
        tv.text = text
        return this
    }

    fun setVisibility(@IdRes viewId: Int, visibility: Int): BaseHolder? {
        if (visibility != getView<View?>(viewId)!!.visibility) {
            getView<View?>(viewId)!!.visibility = visibility
        }
        return this
    }

    fun setImageRes(@IdRes viewId: Int, resId: Int): BaseHolder? {
        val iv: ImageView = getView(viewId)
        iv.setImageResource(resId)
        return this
    }

    fun setTextColor(@IdRes viewId: Int, color: Int): BaseHolder? {
        val tv: TextView = getView(viewId)
        tv.setTextColor(color)
        return this
    }

    fun setBackgroundColor(@IdRes viewId: Int, color: Int): BaseHolder? {
        getView<View?>(viewId)!!.setBackgroundColor(color)
        return this
    }

    fun setBackgroundRes(@IdRes viewId: Int, res: Int): BaseHolder? {
        getView<View?>(viewId)!!.setBackgroundResource(res)
        return this
    }

    /**
     * 添加子点击事件
     *
     * @param viewId 子控件id
     */
    fun addOnChildClickListener(@IdRes viewId: Int): BaseHolder {
        childClickIdS!!.add(viewId)
        val view: View? = getView<View?>(viewId)
        if (!view!!.isClickable) {
            view.isClickable = true
        }
        view.setOnClickListener { v ->
            adapter!!.getOnChildClickListener()!!.click(v, layoutPosition)
        }
        return this
    }

    /**
     * 添加子控件长按事件
     *
     * @param viewId 子控件id
     */
    fun addOnChildLongClickListener(@IdRes viewId: Int) {
        childLongClickIdS!!.add(viewId)
        val view: View? = getView<View?>(viewId)
        if (!view!!.isClickable) {
            view.isClickable = true
        }
        view.setOnLongClickListener { v ->
            adapter!!.getOnChildLongClickListener()!!.longClick(v, layoutPosition)
            true
        }
    }

    fun setAdapter(adapter: BaseAdapter<*>?) {
        this.adapter = adapter
    }
}