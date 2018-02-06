package com.example.yochio.copy_conference_app_2018.data.db.fixeddata

import android.net.Uri
import com.example.yochio.copy_conference_app_2018.BuildConfig
import com.example.yochio.copy_conference_app_2018.R
import com.example.yochio.copy_conference_app_2018.model.AboutApp

/**
 * Created by yotio on 2018/02/06.
 */
class AboutApps {

    companion object {

        fun getThisApps(): List<AboutApp> {
            var index = 0
            return listOf(
                    // Head Item
                    AboutApp.HeadItem(
                            10000 + index++,
                            R.string.about_official_head_title,
                            R.string.about_official_head_description,
                            "",
                            "https://www.facebook.com/DroidKaigi/",
                            "https://twitter.com/droidkaigi",
                            "https://github.com/DroidKaigi/conference-app-2018",
                            "https://www.youtube.com/channel/UCgK6L-PKx2OZBuhrQ6mmQZw",
                            "https://medium.com/droidkaigi"
                    ),
                    // Official site
                    AboutApp.Item(
                            10000 + index++,
                            R.string.about_official_site_title,
                            R.string.about_official_site_description,
                            "https://droidkaigi.jp/2018/"
                    ),
                    // Licenses
                    AboutApp.Item(
                            10000 + index++,
                            R.string.about_licenses_title,
                            R.string.about_licenses_description,
                            getUriBuilder("licenses")
                    ),
                    // Sponsors
                    AboutApp.Item(
                            10000 + index++,
                            R.string.about_sponsors_title,
                            R.string.about_sponsors_description,
                            getUriBuilder("sponsors")
                    ),
                    // Contributors
                    AboutApp.Item(
                            10000 + index++,
                            R.string.about_contributors_title,
                            R.string.about_contributors_description,
                            getUriBuilder("contributors")
                    ),
                    // Dev Version
                    AboutApp.Item(
                            10000 + index,
                            R.string.about_version_title,
                            R.string.about_version_description,
                            // TODO add navigation url
                            ""
                    )
            )
        }

        private fun getUriBuilder(path: String): String {
            val scheme = "conference"
            val host = "droidkaigi.co.jp" +
                    if (BuildConfig.DEBUG) "." + BuildConfig.BUILD_TYPE else ""
            return Uri.Builder()
                    .scheme(scheme)
                    .authority(host)
                    .path(path)
                    .build()
                    .toString()
        }
    }
}