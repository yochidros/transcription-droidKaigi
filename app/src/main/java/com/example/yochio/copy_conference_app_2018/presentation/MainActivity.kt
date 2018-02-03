package com.example.yochio.copy_conference_app_2018.presentation

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.*
import android.support.design.widget.CoordinatorLayout
import android.support.v4.app.Fragment
import com.example.yochio.copy_conference_app_2018.R
import com.example.yochio.copy_conference_app_2018.databinding.ActivityMainBinding
import com.example.yochio.copy_conference_app_2018.di.ViewModelFactory
import com.example.yochio.copy_conference_app_2018.presentation.common.menu.DrawerMenu
import com.example.yochio.copy_conference_app_2018.presentation.common.pref.Prefs
import com.example.yochio.copy_conference_app_2018.presentation.common.view.BottomNavigationBehavior
import com.example.yochio.copy_conference_app_2018.presentation.common.view.BottomNavigationHideBehavior
import com.example.yochio.copy_conference_app_2018.util.ext.disableShiftMode
import com.example.yochio.copy_conference_app_2018.util.ext.elevationForPostLollipop
import com.example.yochio.copy_conference_app_2018.util.ext.observe
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject lateinit var drawerMenu: DrawerMenu
    @Inject lateinit var navigationController: NavigationController
    @Inject lateinit var viewModelFactory: ViewModelFactory

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbar)

        mainViewModel.bottomNavigationBarConfig.observe(this, {
            setBottomNavigationBarBehavior()
        })

        setupBottomNavigationBar(savedInstanceState)
        drawerMenu.setup(binding.drawerLayout, binding.drawer, binding.toolbar, true)
    }

    private fun setupBottomNavigationBar(savedInstanceState: Bundle?) {
        setBottomNavigationBarBehavior()
        binding.bottomNavigation.disableShiftMode()
        binding.bottomNavigation.itemIconTintList = null
        binding.bottomNavigation.setOnNavigationItemSelectedListener({ item ->
            val navigationItem = BottomNavigationItem.forId(item.itemId)

            setupToolbar(navigationItem)

            navigationItem.navigate(navigationController)
            true
        })
        if (savedInstanceState == null) {
            when (intent.getStringExtra("shortcut")) {
                "favorite" -> {
                    binding.bottomNavigation.selectedItemId = R.id.navigation_favorite_sessions
                }
                else -> binding.bottomNavigation.selectedItemId = R.id.navigation_sessions
            }
        }
        binding.bottomNavigation.setOnNavigationItemReselectedListener({ item ->
            val navigationItem = BottomNavigationItem.forId(item.itemId)
            val fragment = supportFragmentManager.findFragmentByTag(navigationItem.name)
            if (fragment is BottomNavigationItem.OnReselectedListener) {
                fragment.onReselected()
            }
        })
    }

    private fun setBottomNavigationBarBehavior() {
        (binding.bottomNavigation.layoutParams as CoordinatorLayout.LayoutParams).behavior =
                if (Prefs.enableHideBottomNavigationBar) {
                    BottomNavigationHideBehavior()
                } else {
                    BottomNavigationBehavior()
                }
    }

    private fun setupToolbar(navigationItem: BottomNavigationItem) {
        binding.toolbar.elevationForPostLollipop = if (navigationItem.isUseToolbarElevation) {
            resources.getDimensionPixelSize(R.dimen.elevation_app_bar).toFloat()
        } else {
            0F
        }
        supportActionBar?.apply {
            title = if (navigationItem.imageRes != null) {
                setDisplayShowTitleEnabled(true)
                setIcon(navigationItem.imageRes)
                null
            } else {
                setDisplayShowHomeEnabled(false)
                setIcon(null)
                getString(navigationItem.titleRes!!)
            }
        }
    }


    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    override fun onBackPressed() {
        if (drawerMenu.closeDrawerIfNeeded()) {
            super.onBackPressed()
        }
    }

    enum class BottomNavigationItem(
            @MenuRes val menuId: Int,
            @StringRes val titleRes: Int?,
            @DrawableRes val imageRes: Int?,
            val isUseToolbarElevation: Boolean,
            val navigate: NavigationController.() -> Unit
    ) {
        SESSION(R.id.navigation_sessions, R.string.feed_title, null, false, {
            navigateToSessions()
        }),
        SEARCH(R.id.navigation_search, R.string.feed_title, null, false, {
            navigateToFeed()
        }),
        FAVORITE(R.id.navigation_favorite_sessions, R.string.feed_title, null, true, {
            navigateToFeed()
        }),
        FEED(R.id.navigation_feed, R.string.feed_title, null, true, {
            navigateToFeed()
        });

        interface OnReselectedListener {
            fun onReselected()
        }

        companion object {
            fun forId(@IdRes id: Int): BottomNavigationItem {
                return values().first { it.menuId == id }
            }
        }
    }

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, MainActivity::class.java)

        fun start(context: Context) {
            createIntent(context).let {
                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(it)
            }
        }
    }
}
