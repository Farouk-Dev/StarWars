package com.example.testmid.utils

import android.content.Context
import android.view.animation.Interpolator
import android.widget.Scroller

class CustomScroller(context: Context, interpolator: Interpolator, private val mDuration: Int) : Scroller(context, interpolator) {

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration)
    }
}
