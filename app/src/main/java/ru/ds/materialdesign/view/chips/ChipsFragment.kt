package ru.ds.materialdesign.view.chips

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat.getColor
import androidx.fragment.app.Fragment
import ru.ds.materialdesign.R

import ru.ds.materialdesign.databinding.FragmentChipsBinding
import ru.ds.materialdesign.view.MainActivity


class ChipsFragment : Fragment() {

    private val KEY_SP_LOCAL = "sp_local"
    private val KEY_CURRENT_THEME_LOCAL = "current_theme_local"
    private lateinit var parentActivity: MainActivity // 1 способ получить родительскую активити

    private var _binding: FragmentChipsBinding? = null
    private val binding: FragmentChipsBinding
        get() = _binding!!

    companion object {
        fun newInstance() = ChipsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChipsBinding.inflate(inflater)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}