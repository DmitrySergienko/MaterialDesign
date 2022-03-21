package ru.ds.materialdesign.view.layouts.coordinator

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

class ButtonBehaviorMyStyle(context: Context, attr:AttributeSet?=null): CoordinatorLayout.Behavior<View>(context,attr) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    )= dependency is AppBarLayout


    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {

        val bar = dependency as AppBarLayout
        val barHeight = bar.height.toFloat()
        val barY = bar.y


        if(abs(barY)>(barHeight*1/2)){
            child.setBackgroundColor(Color.BLACK)
        }else{
            child.visibility = View.VISIBLE
            child.setBackgroundColor(Color.BLUE)
        }

        if(abs(barY)>(barHeight*4/3)){

            child.visibility = View.GONE
        }else{
            child.visibility = View.GONE
            child.alpha = ((barHeight*1)-abs(barY/1))/(barHeight*3/4)
        }

        return super.onDependentViewChanged(parent, child, dependency)
    }

}