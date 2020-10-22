package com.tan.core.widget

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpaceItemDecoration(space:Int,spanCount:Int): RecyclerView.ItemDecoration() {

    private var space:Int=0
    private var spanCount:Int=0
    init {
        this.space=space
        this.spanCount=spanCount
    }
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val column: Int = parent.getChildLayoutPosition(view) % spanCount
        outRect.bottom = space
        outRect.left = column * space / spanCount
        outRect.right = space - (column + 1) * space / spanCount

    }
}