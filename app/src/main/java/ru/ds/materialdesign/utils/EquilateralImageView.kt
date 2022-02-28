package ru.ds.materialdesign.utils

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

//класс мы будем использовать для отображения равносторонних фотографий на всю ширину экрана
class EquilateralImageView @JvmOverloads constructor (
    context: Context, attributeSet: AttributeSet? = null, defStyle: Int = 0) : AppCompatImageView (context,attributeSet,defStyle){

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }

}