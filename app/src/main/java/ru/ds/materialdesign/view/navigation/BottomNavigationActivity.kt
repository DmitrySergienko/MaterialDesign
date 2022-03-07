package ru.ds.materialdesign.view.navigation

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import ru.ds.materialdesign.R
import ru.ds.materialdesign.databinding.ActivityBottomNavigationBinding
import ru.ds.materialdesign.databinding.ActivityNavigationBinding

class BottomNavigationActivity : AppCompatActivity() {

    lateinit var binding: ActivityBottomNavigationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavigation() //Bottom Navigation Item Selector
    }

    private fun initBottomNavigation() {
        val badge = binding.bottomNavigationView.getOrCreateBadge(R.id.bottom_view_mars)  //create badge
        badge?.number = 1
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_view_earth -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, EarthFragment()).commit()
                    binding.bottomNavigationView.removeBadge(R.id.bottom_view_earth)  //remove badge when on click
                    true
                }
                R.id.bottom_view_mars -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, MarsFragment()).commit()
                    binding.bottomNavigationView.removeBadge(R.id.bottom_view_mars) //remove badge when on click
                    true
                }
                R.id.bottom_view_system -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, SystemFragment()).commit()
                    binding.bottomNavigationView.removeBadge(R.id.bottom_view_system) //remove badge when on click
                    true
                }
                else -> true
            }
        }
        //default view
        binding.bottomNavigationView.selectedItemId = R.id.bottom_view_earth


    }
}
