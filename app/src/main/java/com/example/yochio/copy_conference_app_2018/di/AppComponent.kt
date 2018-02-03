package com.example.yochio.copy_conference_app_2018.di

import android.app.Application
import android.arch.lifecycle.ViewModel
import com.example.yochio.copy_conference_app_2018.di.activitymodule.MainActivityBuilder
import com.example.yochio.copy_conference_app_2018.presentation.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by yochio on 2018/01/30.
 */

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    ViewModelModule::class,
    MainActivityBuilder::class
])

interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: App)
}