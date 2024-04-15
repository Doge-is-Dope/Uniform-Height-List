package com.dogeisdope.uniformheightlist

import android.content.res.Resources
import android.view.View


object Utils {

    fun View.getMeasurements(itemWidth: Int): Pair<Int, Int> {
        val widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(
            itemWidth, View.MeasureSpec.EXACTLY
        )
        val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(
            0, View.MeasureSpec.UNSPECIFIED
        )
        measure(widthMeasureSpec, heightMeasureSpec)
        return measuredWidth to measuredHeight
    }

    val Int.pxToDp: Int
        get() = (this / Resources.getSystem().displayMetrics.density).toInt()

    val Int.dpToPx: Int
        get() = (this * Resources.getSystem().displayMetrics.density).toInt()
}