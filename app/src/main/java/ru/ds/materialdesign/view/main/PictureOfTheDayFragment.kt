package ru.ds.materialdesign.view.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar
import ru.ds.materialdesign.R
import ru.ds.materialdesign.databinding.FragmentMainBinding
import ru.ds.materialdesign.utils.showSnackBar
import ru.ds.materialdesign.view.MainActivity
import ru.ds.materialdesign.viewModel.PictureOfTheDayState
import ru.ds.materialdesign.viewModel.PictureOfTheDayViewModel

class PictureOfTheDayFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding!!

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
    }

    //создаем viewModel
    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launchMenuActionBarIcons()
        viewModel.getLiveData().observe(viewLifecycleOwner,
            { renderData(it) }) // viewLifecycleOwner - подписываемя на обноения пока Fragment "жив"
        viewModel.sendServerRequest() //вызываем запрос

        //вешаем слушатель на картинку Wiki
        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }
//BottomSheet view usage variants
        bottomSheetBehavior = BottomSheetBehavior.from(binding.included.bottomSheetContainer)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_SETTLING
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    /*BottomSheetBehavior.STATE_DRAGGING -> TODO("not implemented")
                    BottomSheetBehavior.STATE_COLLAPSED -> TODO("not implemented")
                    BottomSheetBehavior.STATE_EXPANDED -> TODO("not implemented")
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> TODO("not implemented")
                    BottomSheetBehavior.STATE_HIDDEN -> TODO("not implemented")
                    BottomSheetBehavior.STATE_SETTLING -> TODO("not implemented")
                        */

                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                Log.d("Dimas", "$slideOffset")
            }
        })

    }

    //одиваем кноки в меню
    private fun launchMenuActionBarIcons() {
        setHasOptionsMenu(true) // для отображения меню
        (requireActivity() as MainActivity).setSupportActionBar(binding.bottomAppBar)
    }

    fun renderData(pictureOfTheDayState: PictureOfTheDayState) {
        when (pictureOfTheDayState) {
            is PictureOfTheDayState.Error -> {
                Toast.makeText(requireContext(), "No server response", Toast.LENGTH_SHORT).show()
            }
            is PictureOfTheDayState.Loading -> {
               // with(binding) {
               //     mainFragmentLoadingLayout.visibility = View.VISIBLE
               //     mainFragmentRoot.showSnackBar(
               //         "Loading",
               //         "Reloading",
               //         { viewModel.sendServerRequest() }
               //     )
               // }
                Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
            }
            is PictureOfTheDayState.Success -> {
                binding.imageView.load(pictureOfTheDayState.serverResponseData.hdurl) //HD URL
                binding.included.bottomSheetDescriptionHeader.text =
                    pictureOfTheDayState.serverResponseData.title
                binding.included.bottomSheetDescription.text =
                    pictureOfTheDayState.serverResponseData.explanation

            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_fav -> Toast.makeText(requireContext(), "app_bar_fav", Toast.LENGTH_SHORT)
                .show()
            R.id.app_bar_settings -> Toast.makeText(
                requireContext(),
                "app_bar_settings",
                Toast.LENGTH_SHORT
            ).show()
            android.R.id.home -> Toast.makeText(requireContext(), "home", Toast.LENGTH_SHORT).show()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}