package com.example.yochio.copy_conference_app_2018.presentation.about

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.yochio.copy_conference_app_2018.R
import com.example.yochio.copy_conference_app_2018.databinding.ActivityAboutAppBinding
import com.example.yochio.copy_conference_app_2018.presentation.NavigationController
import com.example.yochio.copy_conference_app_2018.presentation.common.menu.DrawerMenu
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Created by yotio on 2018/02/06.
 */
class AboutAppActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject lateinit var dispatchAndroidInjector: DispatchingAndroidInjector<Fragment>
    @Inject lateinit var navigationController: NavigationController
    @Inject lateinit var drawerMenu: DrawerMenu

    private val binding: ActivityAboutAppBinding by lazy {
        DataBindingUtil.setContentView<ActivityAboutAppBinding>(this, R.layout.activity_about_app)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        navigationController.navigateToAboutApp()
        drawerMenu.setup(binding.drawerLayout, binding.drawer, binding.toolbar)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchAndroidInjector

    override fun onBackPressed() {
        if (drawerMenu.closeDrawerIfNeeded()) {
            super.onBackPressed()
        }
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, AboutAppActivity::class.java))
        }
    }
}