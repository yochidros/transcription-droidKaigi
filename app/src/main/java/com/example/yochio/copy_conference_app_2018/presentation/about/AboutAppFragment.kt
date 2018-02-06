package com.example.yochio.copy_conference_app_2018.presentation.about

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yochio.copy_conference_app_2018.di.Injectable
import com.example.yochio.copy_conference_app_2018.presentation.NavigationController
import javax.inject.Inject

/**
 * Created by yotio on 2018/02/06.
 */

class AboutAppFragment : Fragment(), Injectable {
    @Inject lateinit var navigationController: NavigationController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    companion object {
        fun newInstance(): AboutAppFragment = AboutAppFragment()
    }
}