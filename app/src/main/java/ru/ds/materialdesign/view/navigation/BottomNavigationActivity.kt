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
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.bottom_view_earth -> {
                    true
                }
                R.id.bottom_view_mars -> {
                    true
                }
                R.id.bottom_view_system -> {
                    true
                }
                else -> true
            }
        }
    }
}
