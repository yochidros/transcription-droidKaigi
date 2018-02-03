package com.example.yochio.copy_conference_app_2018.di

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

/**
 * Created by yochio on 2018/01/31.
 */
@Module
interface ViewModelModule {
    @Binds fun bindViewModelFactory(factory: ViewModelFactory) : ViewModelProvider.Factory
}