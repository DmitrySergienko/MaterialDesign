package ru.ds.materialdesign.view.ux

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.ds.materialdesign.R
import ru.ds.materialdesign.databinding.ActivityUxBinding

class UXActivity : AppCompatActivity() {
    lateinit var binding: ActivityUxBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_MaterialDesign)
        binding = ActivityUxBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.bottomNavigationViewUX.setOnItemSelectedListener {
            when(it.itemId){
                R.id.fragment_ux_text->{
                    navigateTo(TextUXFragment.newInstance())
                }
                R.id.fragment_ux_button->{
                    navigateTo(ButtonUXFragment.newInstance())
                }
                R.id.fragment_ux_tutorial->{
                    navigateTo(TutorialUXFragment.newInstance())
                }
            }
            true
        }

        binding.bottomNavigationViewUX.selectedItemId = R.id.fragment_ux_text
    }

    fun navigateTo(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.container,fragment).commit()
    }

}