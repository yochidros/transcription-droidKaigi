package com.example.yochio.copy_conference_app_2018.presentation.common.view

import android.animation.Animator
import android.content.Context
import android.support.annotation.Keep
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.View
import java.security.KeyStore

/**
 * Created by yotio on 2018/02/03.
 */
class BottomNavigationHideBehavior : BottomNavigationBehavior {
    @Keep constructor() : super()
    @Keep constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private var isAnimation: Boolean = false
    private var isAcceptNestedScroll: Boolean = false
    private var isProcessedScroll: Boolean = false
    private val animationListener = object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {
        }

        override fun onAnimationEnd(animation: Animator?) {
            isAnimation = false
        }

        override fun onAnimationCancel(animation: Animator?) {
            isAnimation = false
        }

        override fun onAnimationStart(animation: Animator?) {
            isAnimation = true
        }
    }

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: BottomNavigationView, directTargetChild: View, target: View, axes: Int, type: Int): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL && type == ViewCompat.TYPE_TOUCH
    }

    override fun onNestedScrollAccepted(coordinatorLayout: CoordinatorLayout, child: BottomNavigationView, directTargetChild: View, target: View, axes: Int, type: Int) {
        isAcceptNestedScroll = true
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, axes, type)
    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: BottomNavigationView, target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int) {
        isProcessedScroll = true
        when {
            dyConsumed > THRESHOLD_PX -> hideBottomNavigationView(child)
            dyConsumed < THRESHOLD_PX -> showBottomNavigationView(child)
            dyConsumed == 0 && dyUnconsumed < 0 -> isProcessedScroll = false
            else -> Unit
        }
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type)
    }

    override fun onStopNestedScroll(coordinatorLayout: CoordinatorLayout, child: BottomNavigationView, target: View, type: Int) {
        if (isAcceptNestedScroll && !isProcessedScroll) {
            showBottomNavigationView(child)
        }
        isAcceptNestedScroll = false
        isProcessedScroll = false
        super.onStopNestedScroll(coordinatorLayout, child, target, type)
    }

    private fun showBottomNavigationView(view: BottomNavigationView) {
        if (isAnimation) return
        view.animate().apply {
            translationY(0f)
            duration = DURATION_MILLIS
            setListener(animationListener)
            setUpdateListener { updateSnackBarPaddingBottomByBottomNavigationView(view) }
        }
    }

    private fun hideBottomNavigationView(view: BottomNavigationView) {
        if (isAnimation) return
        view.animate().apply {
            translationY(view.height.toFloat())
            duration = DURATION_MILLIS
            setListener(animationListener)
            setUpdateListener { updateSnackBarPaddingBottomByBottomNavigationView(view) }
        }
    }

    companion object {
        const val DURATION_MILLIS: Long = 150L
        const val THRESHOLD_PX: Int = 0
    }
}