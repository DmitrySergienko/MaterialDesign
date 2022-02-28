package ru.ds.materialdesign.view.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar
import ru.ds.materialdesign.R
import ru.ds.materialdesign.databinding.FragmentMainBinding
import ru.ds.materialdesign.utils.showSnackBar
import ru.ds.materialdesign.view.MainActivity
import ru.ds.materialdesign.view.chips.ChipsFragment
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
    private var isMain: Boolean = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLiveData().observe(viewLifecycleOwner,
                { renderData(it) }) // viewLifecycleOwner - подписываемя на обноения пока Fragment "жив"
        viewModel.sendServerRequest() //вызываем запрос

        launchMenuActionBarIcons() // устаналиваем кнопки в меню
        slideFabOnBottomBar() //move FAB on the Bottom Bar
        bottomSheetViewUsageVariants() //BottomSheet view usage variants
        setOnclickWiki() //вешаем слушатель на картинку Wiki
    }

    private fun setOnclickWiki() {
        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                        Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }
    }

    private fun bottomSheetViewUsageVariants() {
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

    private fun slideFabOnBottomBar() {
        binding.fab.setOnClickListener {
            if (isMain) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                binding.bottomAppBar.navigationIcon = null
                binding.bottomAppBar.fabAlignmentMode =
                        BottomAppBar.FAB_ALIGNMENT_MODE_END // кнопку двигаем в конец
                binding.fab.setImageDrawable(
                        ContextCompat.getDrawable(
                                requireContext(),
                                R.drawable.ic_back_fab
                        )
                ) //меняем рисунок кнопки
                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar_other_screen)//меняем меню
            } else {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                binding.bottomAppBar.navigationIcon = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_hamburger_menu_bottom_bar
                )
                binding.bottomAppBar.fabAlignmentMode =
                        BottomAppBar.FAB_ALIGNMENT_MODE_CENTER // кнопку двигаем в центр
                binding.fab.setImageDrawable(
                        ContextCompat.getDrawable(
                                requireContext(),
                                R.drawable.ic_plus_fab
                        )
                ) //меняем рисунок кнопки
                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar)//меняем меню
            }
            isMain = !isMain
        }
    }


    private fun launchMenuActionBarIcons() {
        setHasOptionsMenu(true) // для отображения меню
        (requireActivity() as MainActivity).setSupportActionBar(binding.bottomAppBar)
    }

    fun renderData(pictureOfTheDayState: PictureOfTheDayState) {
        when (pictureOfTheDayState) {
            is PictureOfTheDayState.Error -> {
                with(binding) {
                    mainFragmentLoadingLayout.visibility = View.VISIBLE
                    mainFragmentRoot.showSnackBar(
                            "No server response",
                            "Reloading",
                            { viewModel.sendServerRequest() }
                    )
                }
             }
            is PictureOfTheDayState.Loading -> {
                 with(binding) {
                     mainFragmentLoadingLayout.visibility = View.VISIBLE
                     mainFragmentRoot.showSnackBar(
                         "Loading",
                         "Reloading",
                         { viewModel.sendServerRequest() }
                     )
                 }
            }
            is PictureOfTheDayState.Success -> {
                with(binding){
                mainFragmentLoadingLayout.visibility = View.GONE
                    imageView.load(pictureOfTheDayState.serverResponseData.hdurl) //HD URL
                    included.bottomSheetDescriptionHeader.text =
                            pictureOfTheDayState.serverResponseData.title
                    included.bottomSheetDescription.text =
                            pictureOfTheDayState.serverResponseData.explanation}


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
            R.id.app_bar_settings -> {
                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.container, ChipsFragment.newInstance())
                        .addToBackStack("")
                        .commit()
                Toast.makeText(requireContext(), "app_bar_fav", Toast.LENGTH_SHORT)
                        .show()

            }
            android.R.id.home ->
                BottomNavigationDriverFragment().show(requireActivity().supportFragmentManager, "")
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}