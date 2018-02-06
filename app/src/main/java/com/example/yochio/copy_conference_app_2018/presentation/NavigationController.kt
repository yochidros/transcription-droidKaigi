package com.example.yochio.copy_conference_app_2018.presentation

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.example.yochio.copy_conference_app_2018.R
import com.example.yochio.copy_conference_app_2018.presentation.about.AboutAppActivity
import com.example.yochio.copy_conference_app_2018.presentation.about.AboutAppFragment
import com.example.yochio.copy_conference_app_2018.presentation.common.fragment.Findable
import com.example.yochio.copy_conference_app_2018.presentation.feed.FeedFragment
import com.example.yochio.copy_conference_app_2018.util.CustomTabsHelper
import javax.inject.Inject

/**
 * Created by yochio on 2018/01/30.
 */
class NavigationController @Inject constructor(private val activity: AppCompatActivity) {
    private val containerId: Int = R.id.content
    private val fragmentManager: FragmentManager = activity.supportFragmentManager

    fun navigateToSessions() {
    }

    fun navigateToMainActivity() {
        MainActivity.start(activity)
    }

    fun navigateToFeed() {
        replaceFragment(FeedFragment.newInstance())
    }

    fun navigateToAboutAppActivity() {
        AboutAppActivity.start(activity)
    }

    fun navigateToAboutApp() {
        replaceFragment(AboutAppFragment.newInstance())
    }

    fun navigateImplicitly(url: String?) {
        url?.let {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            intent.resolveActivity(activity.packageManager)?.let {
                activity.startActivity(intent)
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        fragmentManager
                .beginTransaction()
                .replace(containerId, fragment, (fragment as? Findable)?.tagForFinding)
                .commitAllowingStateLoss()
    }

    fun navigateToExternalBrowser(url: String) {
        val uri = run {
            val uri = Uri.parse(url)
            if (uri.host.contains("facebook")) {
                return
            }
            uri
        }
        val intent = Intent(Intent.ACTION_VIEW, uri)
        val intentResolveInfo = activity.packageManager.resolveActivity(
                intent,
                PackageManager.MATCH_DEFAULT_ONLY
        )
        val resolvePackageName = intentResolveInfo.activityInfo.packageName

        val customTabsIntent = CustomTabsIntent.Builder()
                .setShowTitle(true)
                .setToolbarColor(ContextCompat.getColor(activity, R.color.primary))
                .build()
                .apply {
                    val appUri = Uri.parse("android-app://${activity.packageName}")
                    intent.putExtra(Intent.EXTRA_REFERRER, appUri)
                }

        val packageName = CustomTabsHelper.getPackageNameToUse(activity)
        if (resolvePackageName != null && resolvePackageName != packageName) {
            // Open specific app
            activity.startActivity(intent)
            return
        }
        packageName ?: run {
            // Cannot use custom tabs.
            activity.startActivity(customTabsIntent.intent.setData(uri))
            return
        }

        customTabsIntent.intent.`package` = packageName
        customTabsIntent.launchUrl(activity, uri)
    }
}