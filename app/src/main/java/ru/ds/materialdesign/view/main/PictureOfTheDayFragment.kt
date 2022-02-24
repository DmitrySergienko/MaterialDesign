package ru.ds.materialdesign.view.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.ds.materialdesign.R
import ru.ds.materialdesign.databinding.FragmentMainBinding
import ru.ds.materialdesign.viewModel.PictureOfTheDayState
import ru.ds.materialdesign.viewModel.PictureOfTheDayViewModel

class PictureOfTheDayFragment: Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
    get() = _binding !!

    companion object{
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
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner,{renderData(it) }) // viewLifecycleOwner - подписываемя на обноения пока Fragment "жив"
        viewModel.sendServerRequest() //вызываем запрос

        //вешаем слушатель на картинку Wiki
        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
              data = Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }

        BottomSheetBehavior.from(binding.included.bottomSheetContainer)


    }

    fun renderData(pictureOfTheDayState: PictureOfTheDayState){
        when(pictureOfTheDayState){
                is PictureOfTheDayState.Error -> {
                    //TODO HW
                }
                is PictureOfTheDayState.Loading -> {
                    //TODO HW
                }
            is PictureOfTheDayState.Success -> {
                binding.imageView.load(pictureOfTheDayState.serverResponseData.hdurl) //HD URL
                binding.included.bottomSheetDescriptionHeader.text = pictureOfTheDayState.serverResponseData.title
                binding.included.bottomSheetDescription.text = pictureOfTheDayState.serverResponseData.explanation

            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}