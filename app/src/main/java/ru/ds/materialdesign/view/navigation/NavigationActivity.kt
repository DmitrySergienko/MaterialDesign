package ru.ds.materialdesign.view.navigation

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import ru.ds.materialdesign.R
import ru.ds.materialdesign.databinding.ActivityNavigationBinding

class NavigationActivity : AppCompatActivity() {

    lateinit var binding: ActivityNavigationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager)

       //связываем TabLayout с пейджером
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        //binding.tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_earth)
        //binding.tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_mars)
        //binding.tabLayout.getTabAt(2)?.setIcon(R.drawable.ic_system)

        //устанавливаем кастомную иконку
        binding.tabLayout.getTabAt(0)?.setCustomView(R.layout.activity_navigation_tablayout_item_earth)
        binding.tabLayout.getTabAt(1)?.setCustomView(R.layout.activity_navigation_tablayout_item_mars)
        binding.tabLayout.getTabAt(2)?.setCustomView(R.layout.activity_navigation_tablayout_item_system)


    }
}
