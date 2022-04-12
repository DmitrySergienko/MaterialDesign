package ru.ds.materialdesign.view.animations

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.ds.materialdesign.R
import ru.ds.materialdesign.databinding.BottomNavigationAnimationBinding
import ru.ds.materialdesign.view.layouts.explode.ExplodeFragment
import ru.ds.materialdesign.view.layouts.motion.MotionFragment



class BottomNavigationDriverFragmentAnimation  : BottomSheetDialogFragment() {
    private var _binding: BottomNavigationAnimationBinding? = null
    private val binding: BottomNavigationAnimationBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomNavigationAnimationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            binding.navigationAnimation.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.bottom_motion_animation->{
                   val manager = activity?.supportFragmentManager
                    manager?.beginTransaction()
                            ?.replace(R.id.container,MotionFragment.newInstance())
                            ?.addToBackStack("_")
                            ?.commitAllowingStateLoss()
                }
                R.id.bottom_animations_zoom->{
                    startActivity(Intent(requireContext(), AnimationsActivity::class.java))
                }
                R.id.bottom_animations_explode->{
                    val manager = activity?.supportFragmentManager
                    manager?.beginTransaction()
                            ?.replace(R.id.container,ExplodeFragment.newInstance())
                            ?.addToBackStack("_")
                            ?.commitAllowingStateLoss()
                }

            }
                dismiss()
            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}