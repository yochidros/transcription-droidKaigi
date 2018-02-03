package com.example.yochio.copy_conference_app_2018.presentation

import android.annotation.SuppressLint
import android.app.Activity
import android.support.multidex.MultiDexApplication
import com.example.yochio.copy_conference_app_2018.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by yochio on 2018/01/30.
 */
@SuppressLint("Registerd")
open class App : MultiDexApplication(), HasActivityInjector {
    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        setupDagger()
    }

    open fun setupDagger() {
        AppInjector.init(this)
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = dispatchingAndroidInjector

}