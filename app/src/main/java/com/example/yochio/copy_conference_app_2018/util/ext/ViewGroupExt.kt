package com.example.yochio.copy_conference_app_2018.util.ext

import android.view.View
import android.view.ViewGroup

/**
 * Created by yotio on 2018/02/04.
 */

fun ViewGroup.eachChildView(function: (view: View) -> Unit) {
    (0 until childCount).forEach { function(getChildAt(it)) }
}