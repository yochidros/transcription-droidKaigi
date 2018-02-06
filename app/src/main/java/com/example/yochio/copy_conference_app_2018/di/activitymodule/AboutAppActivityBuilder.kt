package com.example.yochio.copy_conference_app_2018.di.activitymodule

import com.example.yochio.copy_conference_app_2018.presentation.about.AboutAppActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by yotio on 2018/02/06.
 */
@Module
interface AboutAppActivityBuilder {
    @ContributesAndroidInjector(modules = [AboutAppActivityModule::class])
    fun contributeAboutAppActivity(): AboutAppActivity
}