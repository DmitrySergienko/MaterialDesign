package ru.ds.materialdesign.view.navigation

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import ru.ds.materialdesign.BuildConfig
import ru.ds.materialdesign.R
import ru.ds.materialdesign.databinding.FragmentEarthBinding
import ru.ds.materialdesign.viewModel.AppState
import ru.ds.materialdesign.viewModel.EpicViewModel


class EarthFragment : Fragment() {


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
        viewModel.getLiveData().observe(viewLifecycleOwner,{ renderData(it)})
        viewModel.sendServerRequest()
    }

    fun renderData(appState: AppState){
        when(appState){
            is AppState.SuccessEpic -> {
                val strDate = appState.serverResponseData.last().date.split(" ").first()
                val image =appState.serverResponseData.last().image
                val url = "https://api.nasa.gov/EPIC/archive/natural/" +
                        strDate.replace("-","/",true) +
                        "/png/" +
                        "$image" +
                        ".png?api_key=${BuildConfig.NASA_API_KEY}"
                binding.epicImageView.load(url)
                binding.epicTextView1.text = appState.serverResponseData.last().caption

            }
            is AppState.Loading -> {
                Toast.makeText(requireContext(),"App Loading Earth Fragment", Toast.LENGTH_SHORT).show()
            }
            is AppState.Error -> {
                Toast.makeText(requireContext(),"App Error Earth Fragment", Toast.LENGTH_SHORT).show()
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