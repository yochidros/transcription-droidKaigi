package com.example.yochio.copy_conference_app_2018.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.example.yochio.copy_conference_app_2018.presentation.App
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector

/**
 * Created by yochio on 2018/01/30.
 */
class AppInjector {
    companion object {
        fun init(app: App) {
            DaggerAppComponent.builder()
                    .application(app)
                    .build().inject(app)

            app.registerActivityLifecycleCallbacks(object: Application.ActivityLifecycleCallbacks{
                override fun onActivityCreated(activity: Activity, p1: Bundle?) {
                    handleActivity(activity)
                }

                override fun onActivityStarted(p0: Activity?) = Unit

                override fun onActivityResumed(p0: Activity?) = Unit

                override fun onActivityPaused(p0: Activity?) = Unit

                override fun onActivityStopped(p0: Activity?) = Unit

                override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) = Unit

                override fun onActivityDestroyed(p0: Activity?) = Unit

            })
        }

        private fun handleActivity(activity: Activity) {
            if (activity is HasSupportFragmentInjector) {
                AndroidInjection.inject(activity)
            }
            val fragmentActivity = activity as? FragmentActivity
            fragmentActivity?.supportFragmentManager?.registerFragmentLifecycleCallbacks(
                    object : FragmentManager.FragmentLifecycleCallbacks() {
                        override fun onFragmentCreated(fm: FragmentManager?, f: Fragment?, savedInstanceState: Bundle?) {
                            if ( f is Injectable ) {
                                AndroidSupportInjection.inject(f)
                            }
                        }
                    }, true
            )
        }
    }
}