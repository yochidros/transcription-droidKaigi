package com.example.yochio.copy_conference_app_2018.di.activitymodule

import com.example.yochio.copy_conference_app_2018.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by yochio on 2018/01/30.
 */
@Module interface MainActivityBuilder {
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    fun contributeMainActivity(): MainActivity
}