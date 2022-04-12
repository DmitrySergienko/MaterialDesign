package ru.ds.materialdesign.view.navigation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.transition.ChangeImageTransform
import android.transition.TransitionManager
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import ru.ds.materialdesign.BuildConfig
import ru.ds.materialdesign.R
import ru.ds.materialdesign.databinding.FragmentEarthBinding
import ru.ds.materialdesign.utils.Constant
import ru.ds.materialdesign.viewModel.AppState
import ru.ds.materialdesign.viewModel.EpicViewModel


class EarthFragment : Fragment() {

    var flag = false //zoomPicture()
    val duration = 1000L

    private var _binding: FragmentEarthBinding? = null
    val binding: FragmentEarthBinding
        get() = _binding!!

    private val viewModel: EpicViewModel by lazy {
        ViewModelProvider(this).get(EpicViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEarthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        viewModel.sendServerRequest()

        //не ясно почему не срабатывает увеличение картинки для "сегодня"
            zoomPicture()// Zoom(crop) picture

        binding.scrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
         binding.header.isSelected = binding.scrollView.canScrollVertically(-1)
         }


        binding.fab.setOnClickListener {
            flag = !flag
            if (flag) {
                ObjectAnimator.ofFloat(binding.plusImageview, View.ROTATION_Y, 0f, 405f).setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.optionOneContainer, View.TRANSLATION_Y, -50f, -260f).setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.optionTwoContainer, View.TRANSLATION_Y, -20f, -130f).setDuration(duration).start()

                binding.optionOneContainer.animate()
                        .alpha(1f)
                        .setDuration(duration / 2)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator?) {
                                super.onAnimationEnd(animation)
                                binding.optionOneContainer.isClickable = true
                            }
                        })
                binding.optionTwoContainer.animate()
                        .alpha(1f)
                        .setDuration(duration / 2)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator?) {
                                super.onAnimationEnd(animation)
                                binding.optionTwoContainer.isClickable = true
                            }
                        })

                binding.transparentBackground.animate()
                        .alpha(0.7f)
                        .setDuration(duration)
            } else {
                ObjectAnimator.ofFloat(binding.plusImageview, View.ROTATION_Y, 405f, 0f).setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.optionOneContainer, View.TRANSLATION_Y, -260f, -50f).setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.optionTwoContainer, View.TRANSLATION_Y, -130f, -20f).setDuration(duration).start()

                binding.optionOneContainer.animate()
                        .alpha(0f)
                        .setDuration(duration / 2)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator?) {
                                super.onAnimationEnd(animation)
                                binding.optionOneContainer.isClickable = false
                            }
                        })
                binding.optionTwoContainer.animate()
                        .alpha(0f)
                        .setDuration(duration / 2)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator?) {
                                super.onAnimationEnd(animation)
                                binding.optionTwoContainer.isClickable = false
                            }
                        })
                binding.transparentBackground.animate()
                        .alpha(0f)
                        .setDuration(duration)


            }
        }
    }


    private fun zoomPicture() {
        binding.epicImageView.setOnClickListener {
            val changeBounds = ChangeImageTransform()
            changeBounds.duration = Constant.DURATION_CROP_ANIMATION_PICTURE
            TransitionManager.beginDelayedTransition(binding.epicRoot, changeBounds)
            flag = !flag
            binding.epicImageView.scaleType = if (flag) ImageView.ScaleType.CENTER_CROP else ImageView.ScaleType.CENTER_INSIDE
        }
    }

    fun renderData(appState: AppState) {
        when (appState) {
            is AppState.SuccessEpic -> {
                val strDate = appState.serverResponseData.last().date.split(" ").first()
                val image = appState.serverResponseData.last().image
                val url = "https://api.nasa.gov/EPIC/archive/natural/" +
                        strDate.replace("-", "/", true) +
                        "/png/" +
                        "$image" +
                        ".png?api_key=${BuildConfig.NASA_API_KEY}"
                binding.epicImageView.load(url)
                // binding.epicTextView1.text = appState.serverResponseData.last().caption
                // binding.epicTextView2.text = appState.serverResponseData.last().date

            }
            is AppState.Loading -> {
                Toast.makeText(requireContext(), "App Loading Earth Fragment", Toast.LENGTH_SHORT).show()
            }
            is AppState.Error -> {
                Toast.makeText(requireContext(), "App Error Earth Fragment", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }


    companion object {
        @JvmStatic
        fun newInstance() = EarthFragment()
    }
}