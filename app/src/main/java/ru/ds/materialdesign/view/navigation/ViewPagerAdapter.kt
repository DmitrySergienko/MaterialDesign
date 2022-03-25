package ru.ds.materialdesign.view.navigation


import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


const val EARTH_KEY = 0
    const val MARS_KEY = 1
    const val SYSTEM_KEY = 2

class ViewPagerAdapter(private val fragmentManager:FragmentActivity):
        FragmentStateAdapter(fragmentManager) {

    //Хранилище фрагметов
    private val fragments = arrayOf(EarthFragment(), MarsFragment())

    override fun getItemCount()= fragments.size // return quantity elements

    override fun createFragment(position: Int)= fragments[position] // return quantity elements



       // return when(position){
       //     EARTH_KEY -> "Earth"
       //     MARS_KEY -> "Mars"
       //     SYSTEM_KEY ->"System"
       //     else -> "Earth"
       // }




}