package ru.ds.materialdesign.view.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ru.ds.materialdesign.R
import ru.ds.materialdesign.databinding.FragmentRecyclerBinding


class RecyclerFragment : Fragment() {


    private var _binding: FragmentRecyclerBinding? = null
    val binding: FragmentRecyclerBinding
        get() = _binding!!
    lateinit var itemTouchHelper:ItemTouchHelper

    val listData = arrayListOf(
            Pair(Data("Earth","Text"),false),
            Pair(Data("Earth","Text"),false),
            Pair(Data("Earth","Text"),false),
            Pair(Data("Earth","Text"),false),
            Pair(Data("Mars",type = TYPE_MARS),false),
            Pair(Data("Mars",type = TYPE_MARS),false),
            Pair(Data("Mars",type = TYPE_MARS),false),
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

        listData.shuffle()
        listData.add(0, Pair(Data(getString(R.string.header), type = TYPE_HEADER), false))
        val adapter = RecyclerAdapter(object : OnClickItemListener {
            override fun onItemClick(data: Data) {
                Toast.makeText(requireContext(), "Тест ${data.name}", Toast.LENGTH_SHORT)
                        .show()
            }
        }, object: OnStartDragListener{
            override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
                itemTouchHelper.startDrag(viewHolder)
            }
        })
        adapter.setData(listData)
        binding.recyclerView.adapter = adapter
        binding.recyclerActivityFAB.setOnClickListener {
            adapter.appendItem()
            binding.recyclerView.smoothScrollToPosition(adapter.itemCount)
        }

        itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(adapter))
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }
    class ItemTouchHelperCallback(val recyclerAdapter:RecyclerAdapter):ItemTouchHelper.Callback() {
        override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
        ): Int {
            val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
            val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
            return makeMovementFlags(dragFlags, swipeFlags)
        }

        override fun onMove(
                recyclerView: RecyclerView,
                from: RecyclerView.ViewHolder,
                to: RecyclerView.ViewHolder
        ): Boolean {
            recyclerAdapter.onItemMove(from.adapterPosition, to.adapterPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            recyclerAdapter.onItemDismiss(viewHolder.adapterPosition)
        }

        override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
            if (viewHolder is RecyclerAdapter.MarsViewHolder)
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE)
                    (viewHolder as RecyclerAdapter.MarsViewHolder).onItemSelected()
        }

        override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
            if (viewHolder is RecyclerAdapter.MarsViewHolder)
                (viewHolder as RecyclerAdapter.MarsViewHolder).onItemClear()
        }
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