package ru.ds.materialdesign.view.layouts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.ds.materialdesign.R
import ru.ds.materialdesign.databinding.ActivityLayoutBinding
import ru.ds.materialdesign.view.layouts.constraint.ConstraintFragment
import ru.ds.materialdesign.view.layouts.coordinator.CoordinatorFragment


class LayoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavigationView()
    }

    private fun initBottomNavigationView() {
        val badge = binding.bottomNavigationView.getOrCreateBadge(R.id.bottom_view_mars)
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_constraint -> {
                    navigationTo(ConstraintFragment())
                    true
                }
                R.id.bottom_coordinator -> {
                    navigationTo(CoordinatorFragment())
                    true
                }
                R.id.bottom_motion -> {
                   // navigationTo(MotionFragment())
                    true
                }
                else -> true
            }
        }
        binding.bottomNavigationView.selectedItemId = R.id.bottom_coordinator


    }

    private fun navigationTo(f: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, f).commit()
    }

}