package ru.ds.materialdesign.view.chips

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.ds.materialdesign.R
import ru.ds.materialdesign.databinding.FragmentChipsBinding
import ru.ds.materialdesign.view.MainActivity
import ru.ds.materialdesign.view.ThemeOne
import ru.ds.materialdesign.view.ThemeSecond


class ChipsFragment : Fragment(), View.OnClickListener {

    private lateinit var parentActivity: MainActivity //get parent activity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentActivity = (requireActivity() as MainActivity) //get parent activity with null check
    }

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
        onClickForTheme() //The click listener for Theme change
    }

    private fun onClickForTheme() {
        binding.btnThemeOne.setOnClickListener(this)
        binding.btnThemeSecond.setOnClickListener(this)

        when (parentActivity.getCurrentTheme()) {
            1 -> binding.radioGroup.check(R.id.btnThemeOne)
            2 -> binding.radioGroup.check(R.id.btnThemeSecond)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnThemeOne -> {
                parentActivity.setCurrentTheme(ThemeOne)
                parentActivity.recreate() // применяем для всей активити и для всех дочерних фрагментов
            }
            R.id.btnThemeSecond -> {
                parentActivity.setCurrentTheme(ThemeSecond)
                parentActivity.recreate()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
