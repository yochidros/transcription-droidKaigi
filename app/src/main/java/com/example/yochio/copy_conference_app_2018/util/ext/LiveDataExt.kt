package com.example.yochio.copy_conference_app_2018.util.ext

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer

/**
 * Created by yotio on 2018/02/04.
 */


fun <T> LiveData<T>.observe(owner: LifecycleOwner, observer: (T?) -> Unit) =
        observe(owner, Observer<T> { v -> observer.invoke(v) })