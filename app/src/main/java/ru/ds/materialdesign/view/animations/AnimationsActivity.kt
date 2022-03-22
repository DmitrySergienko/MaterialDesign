package ru.ds.materialdesign.view.animations

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.*

import ru.ds.materialdesign.databinding.ActivityAnimationsZoomBinding

class AnimationsActivity : AppCompatActivity() {

    lateinit var binding: ActivityAnimationsZoomBinding
    var flag = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationsZoomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageView.setOnClickListener {

            val changeBounds = ChangeImageTransform()
            changeBounds.duration = 1000
            TransitionManager.beginDelayedTransition(binding.container,changeBounds)
            flag = !flag
            binding.imageView.scaleType = if(flag) ImageView.ScaleType.CENTER_CROP else ImageView.ScaleType.CENTER_INSIDE
        }
    }





}