package com.example.yochio.copy_conference_app_2018.di.activitymodule

import android.arch.lifecycle.ViewModel
import android.support.v7.app.AppCompatActivity
import com.example.yochio.copy_conference_app_2018.di.ViewModelKey
import com.example.yochio.copy_conference_app_2018.presentation.MainActivity
import com.example.yochio.copy_conference_app_2018.presentation.MainViewModel
import com.example.yochio.copy_conference_app_2018.presentation.feed.FeedFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by yochio on 2018/01/30.
 */
@Module interface MainActivityModule {
    @Binds fun providesAppCompatActivity(mainActivity: MainActivity): AppCompatActivity

    @ContributesAndroidInjector
    fun contributeFeedFragment(): FeedFragment

    @Binds @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(
            mainViewModel: MainViewModel
    ): ViewModel

}