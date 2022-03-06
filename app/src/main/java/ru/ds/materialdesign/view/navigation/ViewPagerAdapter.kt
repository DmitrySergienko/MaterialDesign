package ru.ds.materialdesign.view.navigation


import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


private const val EARTH_KEY = 0
private const val MARS_KEY = 1
private const val SYSTEM_KEY = 2

class ViewPagerAdapter(private val fragmentManager:FragmentManager): FragmentStatePagerAdapter(fragmentManager) {

    //Хранилище фрагметов
    private val fragments = arrayOf(EarthFragment(), MarsFragment(), SystemFragment())

    override fun getCount()= fragments.size // return quantity elements
    override fun getItem(position: Int)= fragments[position] // return quantity elements

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            EARTH_KEY -> "Earth"
            MARS_KEY -> "Mars"
            SYSTEM_KEY ->"System"
            else -> "Earth"
        }
    }

}