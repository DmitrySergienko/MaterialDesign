package ru.ds.materialdesign.view.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import ru.ds.materialdesign.databinding.FragmentRecyclerBinding


class RecyclerFragment : Fragment() {


    private var _binding: FragmentRecyclerBinding? = null
    val binding: FragmentRecyclerBinding
        get() = _binding!!

    val data = arrayListOf(
            Data("Earth","Text"),
            Data("Earth","Text"),
            Data("Earth","Text"),
            Data("Earth","Text"),
            Data("Earth","Text"),
            Data("Earth","Text"),
            Data("Mars",type = TYPE_MARS),
            Data("Mars",type = TYPE_MARS),
            Data("Mars",type = TYPE_MARS),
    )

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        data.shuffle() //shuffle items in the recycle list

        val adapter = RecyclerAdapter(object :OnClickItemListener{
            override fun onItemClick(data: Data) {
                Toast.makeText(requireContext(),"Мы супер ${data.name}",Toast.LENGTH_SHORT).show()
            }
        })
        adapter.setData(data)
        binding.recyclerView.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = RecyclerFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}