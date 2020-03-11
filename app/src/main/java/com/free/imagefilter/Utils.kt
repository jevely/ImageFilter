package com.free.imagefilter

import android.graphics.Point


fun getScreen(): Point {
    val dm = FilterApplication.getContext()!!.resources.displayMetrics
    return Point(dm.widthPixels, dm.heightPixels)
}

