package com.tan.toollibrary.tool

import android.graphics.drawable.GradientDrawable

object GradientDrawableTool {

    /**
     * 得到实心的drawable, 一般作为选中，点中的效果
     *
     * @param cornerRadius 圆角半径
     * @param solidColor   实心颜色
     * @return 得到实心效果
     */
    fun getSolidRectDrawable(cornerRadius: Int, solidColor: Int): GradientDrawable? {
        val gradientDrawable = GradientDrawable()
        // 设置矩形的圆角半径
        gradientDrawable.cornerRadius = cornerRadius.toFloat()
        // 设置绘画图片色值
        gradientDrawable.setColor(solidColor)
        // 绘画的是矩形
        gradientDrawable.gradientType = GradientDrawable.RADIAL_GRADIENT
        return gradientDrawable
    }

    /**
     * 得到空心的效果，一般作为默认的效果
     *
     * @param cornerRadius 圆角半径
     * @param solidColor   实心颜色
     * @param strokeColor  边框颜色
     * @param strokeWidth  边框宽度
     * @return 得到空心效果
     */
    fun getStrokeRectDrawable(cornerRadius: Int, solidColor: Int, strokeColor: Int, strokeWidth: Int): GradientDrawable? {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.setStroke(strokeWidth, strokeColor)
        gradientDrawable.setColor(solidColor)
        gradientDrawable.cornerRadius = cornerRadius.toFloat()
        gradientDrawable.gradientType = GradientDrawable.RADIAL_GRADIENT
        return gradientDrawable
    }
}