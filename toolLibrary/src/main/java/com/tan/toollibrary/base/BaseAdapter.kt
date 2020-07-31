package com.tan.toollibrary.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tan.toollibrary.tool.CommonTool


abstract class BaseAdapter<T>(protected var mContext: Context, protected val mData: MutableList<T>?) :
    RecyclerView.Adapter<BaseHolder>() {
    private var mOnItemClickListener: OnItemClickListener? = null
    private var mOnItemLongClickListener: OnItemLongClickListener? = null
    private var mOnChildClickListener: OnChildClickListener? = null
    private var mOnChildLongClickListener: OnChildLongClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        val view = LayoutInflater.from(mContext).inflate(initLayout(viewType), parent, false)
        val holder = BaseHolder(view)
        holder.itemView.setOnClickListener {
            if (!CommonTool.isFastDoubleClick()) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener!!.click(holder.itemView.tag as Int)
                }
            }
        }
        holder.itemView.setOnLongClickListener {
            if (mOnItemLongClickListener != null) {
                mOnItemLongClickListener!!.longClick(holder.itemView.tag as Int)
            }
            true
        }
        holder.setAdapter(this)
        return holder
    }

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        if (mData != null) {
            holder.itemView.tag = position
        }
        convert(holder, mData!![position], position)
    }

    override fun getItemCount(): Int {
        return mData?.size ?: 0
    }

    protected abstract fun initLayout(viewType: Int): Int
    fun getItem(pos: Int): T {
        return mData!![pos]
    }

    val data: List<T>?
        get() = mData

    fun setNewData(data: List<T>?) {
        mData!!.clear()
        mData.addAll(data!!)
        notifyDataSetChanged()
    }

    fun setMoreData(data: List<T>?) {
        mData!!.addAll(data!!)
        notifyDataSetChanged()
    }

    fun clear() {
        mData!!.clear()
        notifyDataSetChanged()
    }

    fun loadMore(data: List<T>?) {
        mData!!.addAll(data!!)
        notifyDataSetChanged()
    }

    fun addItem(pos: Int, item: T) {
        mData!!.add(pos, item)
        notifyItemInserted(pos)
    }

    fun addItem(item: T) {
        mData!!.add(item)
        notifyItemInserted(mData.size - 1)
    }

    fun removeItem(pos: Int) {
        mData!!.removeAt(pos)
        notifyItemRemoved(pos)
    }

    /**
     * @param holder
     * @param item
     * @param position
     */
    protected abstract fun convert(holder: BaseHolder, item: T, position: Int)
    interface OnItemClickListener {
        fun click(position: Int)
    }

    interface OnItemLongClickListener {
        fun longClick(position: Int)
    }

    interface OnChildClickListener {
        fun click(view: View?, position: Int)
    }

    interface OnChildLongClickListener {
        fun longClick(view: View?, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        mOnItemClickListener = listener
    }

    fun setOnItemLongClickListener(listener: OnItemLongClickListener?) {
        mOnItemLongClickListener = listener
    }

    fun setOnChildClickListener(listener: OnChildClickListener) {
        mOnChildClickListener = listener
    }

    fun setOnChildLongClickListener(listener: OnChildLongClickListener) {
        mOnChildLongClickListener = listener
    }

     fun getOnChildClickListener(): OnChildClickListener? {
        return mOnChildClickListener
    }

    fun getOnChildLongClickListener(): OnChildLongClickListener? {
        return mOnChildLongClickListener
    }
}