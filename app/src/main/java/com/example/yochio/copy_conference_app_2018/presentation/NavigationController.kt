package com.example.yochio.copy_conference_app_2018.presentation

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.example.yochio.copy_conference_app_2018.R
import com.example.yochio.copy_conference_app_2018.presentation.about.AboutAppActivity
import com.example.yochio.copy_conference_app_2018.presentation.about.AboutAppFragment
import com.example.yochio.copy_conference_app_2018.presentation.common.fragment.Findable
import com.example.yochio.copy_conference_app_2018.presentation.feed.FeedFragment
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

    private fun replaceFragment(fragment: Fragment) {
        fragmentManager
                .beginTransaction()
                .replace(containerId, fragment, (fragment as? Findable)?.tagForFinding)
                .commitAllowingStateLoss()
    }
}