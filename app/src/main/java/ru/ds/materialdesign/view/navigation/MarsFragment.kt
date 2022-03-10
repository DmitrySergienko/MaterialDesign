package ru.ds.materialdesign.view.navigation

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import ru.ds.materialdesign.R
import ru.ds.materialdesign.databinding.FragmentMarsBinding
import ru.ds.materialdesign.databinding.FragmentSystemBinding
import ru.ds.materialdesign.viewModel.AppState
import ru.ds.materialdesign.viewModel.MarsViewModel

class MarsFragment : Fragment() {


    private var _binding: FragmentMarsBinding? = null
    val binding: FragmentMarsBinding
        get() = _binding!!

    private val viewModel: MarsViewModel by lazy {
        ViewModelProvider(this).get(MarsViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner,{ renderData(it)})
        viewModel.sendServerRequest()
    }

    fun renderData(appState: AppState){
        when(appState){
            is AppState.SuccessMars -> {
            binding.marsImageView.load(appState.serverResponseData.photos.first().imgSrc)
            binding.marsCameraNameTextV.text = appState.serverResponseData.photos.first().camera.name
            binding.marsRoverTextView.text = appState.serverResponseData.photos.first().rover.name
            binding.marsRoverCameraTextView.text = appState.serverResponseData.photos.first().camera.fullName
            }
            is AppState.Loading -> {
                Toast.makeText(requireContext(),"App Loading", Toast.LENGTH_SHORT).show()
            }
            is AppState.Error -> {
                Toast.makeText(requireContext(),"App Error", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }



    companion object {
        @JvmStatic
        fun newInstance() = MarsFragment()
    }
}