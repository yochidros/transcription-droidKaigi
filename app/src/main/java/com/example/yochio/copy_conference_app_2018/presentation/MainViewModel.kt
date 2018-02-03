package com.example.yochio.copy_conference_app_2018.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.example.yochio.copy_conference_app_2018.R
import javax.inject.Inject

/**
 * Created by yochio on 2018/01/31.
 */
class MainViewModel @Inject constructor(context: Context) : ViewModel() {
    private val mutableLocalTimeConfig: MutableLiveData<Boolean> = MutableLiveData()
    val localTimeConfig: LiveData<Boolean> = mutableLocalTimeConfig
    private val mutableBottomNavigationBarConfig: MutableLiveData<Boolean> = MutableLiveData()
    val bottomNavigationBarConfig: LiveData<Boolean> = mutableBottomNavigationBarConfig
    private val mutableLastTimeZoneChangeIntent: MutableLiveData<Intent> = MutableLiveData()
    val lastTimeZoneChangeIntent: LiveData<Intent> = mutableLastTimeZoneChangeIntent

    init {

    }

    override fun onCleared() {
        super.onCleared()
    }
}