package com.example.testmid.utils

import android.support.v4.view.ViewPager
import android.view.View
import android.widget.RelativeLayout

import com.example.testmid.R
import com.facebook.drawee.view.SimpleDraweeView


class MoviePageTransformer : ViewPager.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        val shadow = view.findViewById<RelativeLayout>(R.id.layout_shadow)
        val picture = view.findViewById<SimpleDraweeView>(R.id.picture)
        // Get page index from tag

        val pageWidth = view.width
        val absPosition = Math.abs(position)

        if (position <= -1.0f || position >= 1.0f) {
            // Page is not visible -- stop any running animations
            shadow!!.alpha = 0f

        } else if (position == 0.0f) {

            // Page is selected -- reset any views if necessary
            shadow!!.alpha = 0f
        } else {

            // Page is currently being swiped -- perform animations here
            if (1 - absPosition >= 0.8)
                if (shadow != null) {
                    shadow.translationX = ((-(1 - position)).toDouble() * 0.1 * pageWidth.toDouble() * 2.0).toFloat()
                    shadow.alpha = 1 - (1 - absPosition)
                }


            if (position < 0.0f)
                view.alpha = 1f
            else {
                view.alpha = 1 - absPosition
                //Half the normal speed
                picture.translationX = -position * (pageWidth / 2)
            }

        }
    }
}