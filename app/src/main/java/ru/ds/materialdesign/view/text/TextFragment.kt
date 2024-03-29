package ru.ds.materialdesign.view.text


import android.os.Build
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.SpannedString
import android.text.style.DynamicDrawableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import ru.ds.materialdesign.R
import ru.ds.materialdesign.databinding.FragmentTextBinding
import ru.ds.materialdesign.viewModel.DataModel

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


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataModel.textDescriptionFromNASA.observe(activity as LifecycleOwner, {
            binding.textDescription.text = it


           //  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
           //      binding.textDescription.typeface = resources.getFont(R.font.robus)
           //  }else{
           //      binding.textDescription.typeface = Typeface.createFromAsset(requireActivity().assets,
           //          "folder1/Robus-BWqOd.otf")
           //  }


            val text = it
            var spannableStringBuilder = SpannableStringBuilder(text)
            val spannableString = SpannableString(text)
            val spannedString = SpannedString(spannableStringBuilder)

            //работа с textView через буфер
            binding.textDescription.setText(spannableStringBuilder, TextView.BufferType.EDITABLE)
            //binding.included.bottomSheetDescription.setText(spannableString,TextView.BufferType.SPANNABLE)
            spannableStringBuilder = binding.textDescription.text as SpannableStringBuilder

            spannableStringBuilder.setSpan(ForegroundColorSpan(ContextCompat.getColor(requireContext(),
                    R.color.colorAccent)), 0, spannableStringBuilder.length / 2,
                    SpannableString.SPAN_INCLUSIVE_INCLUSIVE
            )
            spannableStringBuilder.setSpan(
                    ForegroundColorSpan(
                            ContextCompat.getColor(
                                    requireContext(),
                                    R.color.teal_200
                            )
                    ),
                    spannableStringBuilder.length / 2,
                    spannableStringBuilder.length,
                    SpannableString.SPAN_INCLUSIVE_INCLUSIVE
            )

            spannableStringBuilder.indices.forEach {
                if (spannableStringBuilder[it] == ('.')) {
                    spannableStringBuilder.setSpan(
                            ImageSpan(
                                    requireContext(), R.drawable.ic_earth,
                                    DynamicDrawableSpan.ALIGN_CENTER
                            ), it, it + 1, SpannableString.SPAN_INCLUSIVE_INCLUSIVE
                    )
                }
            }
            binding.textDescription.text = spannableStringBuilder
        })

    }

    companion object {
        @JvmStatic
        fun newInstance() = TextFragment()
    }
}