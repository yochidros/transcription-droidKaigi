package com.example.yochio.copy_conference_app_2018.presentation.about.item

import com.xwray.groupie.Section
import com.xwray.groupie.Item
import com.example.yochio.copy_conference_app_2018.model.AboutApp

/**
 * Created by yotio on 2018/02/06.
 *
 */

class AboutAppsSection : Section() {

    fun updateAboutApps(
            aboutApps: List<AboutApp>,
            onAboutHeaderIconClickListener: (String) -> Unit?
    ) {
        val headItem = aboutApps.first { it is AboutApp.HeadItem } as AboutApp.HeadItem
        val header = AboutAppHeaderItem(
                headItem,
                onAboutHeaderIconClickListener
        )
        val list = mutableListOf<Item<*>>(header)
        aboutApps.filter { it is AboutApp.Item }
                .mapTo(list) {
                    AboutAppItem(it)
                }
        update(list)
    }

}