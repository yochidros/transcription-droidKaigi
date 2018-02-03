package com.example.yochio.copy_conference_app_2018.di

import android.arch.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Created by yochio on 2018/01/31.
 */

@MustBeDocumented @Target(
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey internal annotation class ViewModelKey(val value: KClass<out ViewModel>)