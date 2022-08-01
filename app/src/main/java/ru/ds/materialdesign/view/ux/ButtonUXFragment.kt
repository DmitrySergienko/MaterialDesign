package ru.ds.materialdesign.view.ux

import ru.ds.materialdesign.databinding.FragmentUxButtonBinding

class ButtonUXFragment : ViewBindingFragment<FragmentUxButtonBinding>(FragmentUxButtonBinding::inflate) {

    companion object{
        fun newInstance() = ButtonUXFragment()
    }

}