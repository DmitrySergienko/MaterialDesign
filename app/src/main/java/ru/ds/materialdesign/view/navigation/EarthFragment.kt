package ru.ds.materialdesign.view.navigation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import ru.ds.materialdesign.R
import ru.ds.materialdesign.databinding.FragmentEarthBinding


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


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }



    companion object {
        @JvmStatic
        fun newInstance() = EarthFragment()
    }
}