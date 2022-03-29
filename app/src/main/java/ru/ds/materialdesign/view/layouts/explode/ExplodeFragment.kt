package ru.ds.materialdesign.view.layouts.explode

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.*
import ru.ds.materialdesign.R
import ru.ds.materialdesign.databinding.FragmentExplodeBinding


class ExplodeFragment : Fragment() {


    private var _binding: FragmentExplodeBinding? = null
    val binding: FragmentExplodeBinding
        get() = _binding!!



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExplodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter =Adapter()
    }

    private fun explode(clickedView: View){
        val set = TransitionSet()
        val explode = Explode()
        val fade = Fade()
        fade.duration = 2000
        var rect = Rect()
        clickedView.getGlobalVisibleRect(rect)
        explode.duration = 1000
        explode.excludeTarget(clickedView,true)
        set.addTransition(explode)
        set.addTransition(fade)
        explode.epicenterCallback = object: Transition.EpicenterCallback(){
            override fun onGetEpicenter(transition: Transition): Rect {
                return rect
            }
        }
        TransitionManager.beginDelayedTransition(binding.recyclerView,set)
        binding.recyclerView.adapter =null
    }

    inner class Adapter : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                            R.layout.fragment_animations_explode_recycler_item,
                            parent,
                            false
                    ) as View
            )
        }


        override fun getItemCount(): Int {
            return 32
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.itemView.setOnClickListener {
                explode(it)
            }
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = ExplodeFragment()
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}