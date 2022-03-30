package ru.ds.materialdesign.view.text

import android.os.Bundle
import android.transition.ChangeImageTransform
import android.transition.TransitionManager
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import ru.ds.materialdesign.R
import ru.ds.materialdesign.databinding.FragmentMarsBinding
import ru.ds.materialdesign.databinding.FragmentTextBinding
import ru.ds.materialdesign.utils.Constant.DURATION_CROP_ANIMATION_PICTURE
import ru.ds.materialdesign.utils.showSnackBar
import ru.ds.materialdesign.view.main.PictureOfTheDayFragment
import ru.ds.materialdesign.viewModel.AppState
import ru.ds.materialdesign.viewModel.DataModel
import ru.ds.materialdesign.viewModel.MarsViewModel

class TextFragment : Fragment() {
    private val dataModel: DataModel by activityViewModels()

    private var _binding: FragmentTextBinding? = null
    val binding: FragmentTextBinding
        get() = _binding!!


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTextBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataModel.textDescriptionFromNASA.observe(activity as LifecycleOwner,{
            binding.textDescription.text =it
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = TextFragment()
    }
}