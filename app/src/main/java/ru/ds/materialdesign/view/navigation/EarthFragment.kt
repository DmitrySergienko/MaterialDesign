package ru.ds.materialdesign.view.navigation

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import coil.api.load
import ru.ds.materialdesign.R
import ru.ds.materialdesign.databinding.FragmentEarthBinding
import ru.ds.materialdesign.viewModel.AppState


class EarthFragment : Fragment() {


    private var _binding: FragmentEarthBinding? = null
    val binding: FragmentEarthBinding
        get() = _binding!!



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
    }

    fun renderData(appState: AppState){
        when(appState){
            is AppState.SuccessEpic -> {
               // binding.epicImageView.load(appState.serverResponseData.)

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
        fun newInstance() = EarthFragment()
    }
}