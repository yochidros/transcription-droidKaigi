package com.example.yochio.copy_conference_app_2018.util.ext

import android.os.Build
import android.view.View

/**
 * Created by yotio on 2018/02/04.
 */


var View.elevationForPostLollipop: Float
    get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        elevation
    } else {
        0F
    }
    set(value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            elevation = value
        }
    }
