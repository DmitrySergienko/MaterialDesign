package ru.ds.materialdesign.view.ux

import ru.ds.materialdesign.databinding.FragmentUxTextBinding


class TextUXFragment : ViewBindingFragment<FragmentUxTextBinding>(FragmentUxTextBinding::inflate) {

    companion object{
        fun newInstance() = TextUXFragment()
    }



}