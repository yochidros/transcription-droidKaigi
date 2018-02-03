package com.example.yochio.copy_conference_app_2018.presentation.common.pref

import com.chibatching.kotpref.KotprefModel
import com.example.yochio.copy_conference_app_2018.R
import com.example.yochio.copy_conference_app_2018.util.ext.bool

/**
 * Created by yotio on 2018/02/03.
 */
object Prefs : KotprefModel() {
    public override val kotprefName: String = "droidkaigi_prefs"

    var enableLocalTime: Boolean by booleanPref(
            context.bool(R.bool.pref_default_value_enable_local_time),
            R.string.pref_key_enable_local_time
    )

    var enableNotification: Boolean by booleanPref(
            context.bool(R.bool.pref_default_value_enable_notification),
            R.string.pref_key_enable_notification
    )

    var enableHideBottomNavigationBar: Boolean by booleanPref(
            context.bool(R.bool.pref_default_value_enable_hide_bottom_navigation),
            R.string.pref_key_enable_hide_bottom_navigation
    )
}