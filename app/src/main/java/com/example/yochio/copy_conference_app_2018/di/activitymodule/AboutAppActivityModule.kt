package com.example.yochio.copy_conference_app_2018.di.activitymodule

import android.support.v7.app.AppCompatActivity
import com.example.yochio.copy_conference_app_2018.presentation.about.AboutAppActivity
import com.example.yochio.copy_conference_app_2018.presentation.about.AboutAppFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by yotio on 2018/02/06.
 */
@Module
interface AboutAppActivityModule {
    @Binds
    fun providesAppCompatActivity(activity: AboutAppActivity): AppCompatActivity

    @ContributesAndroidInjector
    fun contributeAboutAppFragment(): AboutAppFragment
}