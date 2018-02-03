package com.example.yochio.copy_conference_app_2018.util.ext

import android.content.Context
import android.support.annotation.BoolRes

/**
 * Created by yotio on 2018/02/03.
 */

fun Context.bool(@BoolRes boolRes: Int): Boolean = resources.getBoolean(boolRes)