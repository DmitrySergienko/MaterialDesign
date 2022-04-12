package ru.ds.materialdesign.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.ds.materialdesign.R
import ru.ds.materialdesign.databinding.ActivityMainBinding
import ru.ds.materialdesign.view.ux.SplashFragment

const val ThemeOne = 1
const val ThemeSecond = 2


class MainActivity : AppCompatActivity(){

    private val KEY_SP = "sp"
    private val KEY_CURRENT_THEME = "current_theme"
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(getRealStyle(getCurrentTheme()))
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)

       if(savedInstanceState == null){
           supportFragmentManager
               .beginTransaction()
               .replace(R.id.container, SplashFragment.newInstance())
               .commit()
       }
    }

    fun setCurrentTheme(currentTheme: Int){
        val sharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(KEY_CURRENT_THEME,currentTheme)
        editor.apply()
    }

    fun getCurrentTheme(): Int {
        val sharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        return sharedPreferences.getInt(KEY_CURRENT_THEME, -1)
    }

   private fun getRealStyle(currentTheme: Int): Int {
       return when (currentTheme) {
           ThemeOne -> R.style.MyBlueOriginalTheme
           ThemeSecond -> R.style.MyGreyOriginalTheme
           else -> 0
       }
   }
}