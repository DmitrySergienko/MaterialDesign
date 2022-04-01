package ru.ds.materialdesign.view.ux

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.ds.materialdesign.databinding.FragmentUxTutorialBinding
import smartdevelop.ir.eram.showcaseviewlib.GuideView
import smartdevelop.ir.eram.showcaseviewlib.config.DismissType
import smartdevelop.ir.eram.showcaseviewlib.config.Gravity
import smartdevelop.ir.eram.showcaseviewlib.listener.GuideListener


class TutorialUXFragment : Fragment() {
    private var _binding: FragmentUxTutorialBinding? = null
    private val binding: FragmentUxTutorialBinding
        get() = _binding!!

    companion object{
        fun newInstance() = TutorialUXFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUxTutorialBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val builder = GuideView.Builder(requireContext())
            .setTitle("Title tutorial 1")
            .setContentText("We do not work with color, size and fonts")
            .setGravity(Gravity.center)
            .setDismissType(DismissType.anywhere)
            .setTargetView(binding.btnBad)
            .setDismissType(DismissType.anywhere)
            .setGuideListener(object : GuideListener {
                override fun onDismiss(view: View?) {

                    val builder2 = GuideView.Builder(requireContext())
                        .setTitle("Title tutorial 1")
                        .setContentText("We use work ONLY with transparencies and space")
                        .setGravity(Gravity.center)
                        .setDismissType(DismissType.anywhere)
                        .setTargetView(binding.btnGood)
                        .setDismissType(DismissType.anywhere)
                        .setGuideListener(object : GuideListener {
                            override fun onDismiss(view: View?) {
                                // сохранить в SP то что уже показали, и больше не надо
                            }
                        })
                    builder2.build().show()

                }
            })
        builder.build().show()

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}