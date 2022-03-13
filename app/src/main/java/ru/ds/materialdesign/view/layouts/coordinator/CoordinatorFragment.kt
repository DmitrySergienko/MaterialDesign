package ru.ds.materialdesign.view.layouts.coordinator

import android.os.Bundle
import android.view.*
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import ru.ds.materialdesign.databinding.FragmentCoordinatorBinding


class CoordinatorFragment : Fragment() {


    private var _binding: FragmentCoordinatorBinding? = null
    val binding: FragmentCoordinatorBinding
        get() = _binding!!



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoordinatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

                // прописываем behavior через код
        val behavior = ButtonBehavior(requireContext())
        (binding.myButton.getLayoutParams() as CoordinatorLayout.LayoutParams).behavior = behavior

    }

    companion object {
        @JvmStatic
        fun newInstance() = CoordinatorFragment()
    }
}