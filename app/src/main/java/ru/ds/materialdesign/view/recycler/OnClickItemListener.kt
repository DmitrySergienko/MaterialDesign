package ru.ds.materialdesign.view.recycler

import androidx.recyclerview.widget.RecyclerView

interface OnClickItemListener {

    fun onItemClick(data:Data)
}

fun interface OnStartDragListener {
    fun onStartDrag(view: RecyclerView.ViewHolder) // двигаем все элементы RecyclerView.ViewHolder
}