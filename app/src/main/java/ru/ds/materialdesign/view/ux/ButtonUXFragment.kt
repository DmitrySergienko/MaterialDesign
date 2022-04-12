package ru.ds.materialdesign.view.ux

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.ds.materialdesign.databinding.FragmentUxButtonBinding

class ButtonUXFragment : ViewBindingFragment<FragmentUxButtonBinding>(FragmentUxButtonBinding::inflate) {

    companion object{
        fun newInstance() = ButtonUXFragment()
    }

}