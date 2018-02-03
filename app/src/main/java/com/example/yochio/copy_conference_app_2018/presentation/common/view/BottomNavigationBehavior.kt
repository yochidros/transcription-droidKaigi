package com.example.yochio.copy_conference_app_2018.presentation.common.view

import android.content.Context
import android.support.annotation.Keep
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.util.AttributeSet
import android.view.View

/**
 * Created by yotio on 2018/02/03.
 */
open class BottomNavigationBehavior : CoordinatorLayout.Behavior<BottomNavigationView> {

    @Keep constructor() : super()

    @Keep constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private var isSnackbarAppear: Boolean = false
    private var snackbar: Snackbar.SnackbarLayout? = null

    override fun layoutDependsOn(parent: CoordinatorLayout?, child: BottomNavigationView?, dependency: View?): Boolean {
        return dependency is Snackbar.SnackbarLayout
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout?, child: BottomNavigationView?, dependency: View?): Boolean {
        if (dependency is Snackbar.SnackbarLayout) {
            if (isSnackbarAppear) return true
            isSnackbarAppear = true
            snackbar = dependency
            child?.let { updateSnackBarPaddingBottomByBottomNavigationView(it) }
            return true
        }
        return false
    }

    override fun onDependentViewRemoved(parent: CoordinatorLayout?, child: BottomNavigationView?, dependency: View?) {
        when (dependency) {
            is Snackbar.SnackbarLayout -> {
                isSnackbarAppear = false
                snackbar = null
            }
            else -> super.onDependentViewRemoved(parent, child, dependency)
        }
    }
    internal fun updateSnackBarPaddingBottomByBottomNavigationView(view: BottomNavigationView) {
        snackbar?.apply {
            val translateY = (view.height - view.translationY).toInt()
            setPadding(paddingLeft, paddingTop, paddingRight, translateY)
            requestLayout()
        }
    }
}