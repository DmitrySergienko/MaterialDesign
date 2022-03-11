package ru.ds.materialdesign.view.layouts

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import ru.ds.materialdesign.R
import ru.ds.materialdesign.databinding.FragmentChipsBinding
import ru.ds.materialdesign.databinding.FragmentConstraintBinding
import ru.ds.materialdesign.databinding.FragmentEarthBinding
import ru.ds.materialdesign.viewModel.AppState


class ConstraintFragment : Fragment() {


    private var _binding: FragmentConstraintBinding? = null
    val binding: FragmentConstraintBinding
        get() = _binding!!



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConstraintBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }




    companion object {
        @JvmStatic
        fun newInstance() = ConstraintFragment()
    }
}