package ru.ds.materialdesign.view.ux

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.ds.materialdesign.R
import ru.ds.materialdesign.databinding.SplashFragmentBinding
import ru.ds.materialdesign.view.main.PictureOfTheDayFragment
import ru.ds.materialdesign.viewModel.MarsViewModel

class SplashFragment : Fragment() {

    var flag = false

    private var _binding: SplashFragmentBinding? = null
    val binding: SplashFragmentBinding
        get() = _binding!!

    private val viewModel: MarsViewModel by lazy {
        ViewModelProvider(this).get(MarsViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = SplashFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.iv.animate().rotationY(360f).setDuration(3000L).start()

        val cdt = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                requireActivity().supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                        .commit()
            }


        }
        cdt.start()
    }

        companion object {
            @JvmStatic
            fun newInstance() = SplashFragment()
        }

}
