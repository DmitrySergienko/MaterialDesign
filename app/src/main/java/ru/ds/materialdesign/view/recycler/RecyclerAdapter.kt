package ru.ds.materialdesign.view.recycler

import android.graphics.Color
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MotionEventCompat
import androidx.recyclerview.widget.RecyclerView
import ru.ds.materialdesign.databinding.FragmenRecyclerItemEarthBinding
import ru.ds.materialdesign.databinding.FragmentRecyclerItemHeaderBinding
import ru.ds.materialdesign.databinding.FragmentRecyclerItemMarsBinding

class RecyclerAdapter(val onClickItemListener: OnClickItemListener, val onStartDragListener: OnStartDragListener) :
        RecyclerView.Adapter<RecyclerAdapter.BaseViewHolder>(),
        ItemTouchHelperAdapter {

    private lateinit var listData: MutableList<Pair<Data, Boolean>>
    fun setData(listData: MutableList<Pair<Data, Boolean>>) {
        this.listData = listData
    }

    //заполняем список в зависимости от типа шаблона
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            TYPE_EARTH -> {
                val binding = FragmenRecyclerItemEarthBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false)
                EarthViewHolder(binding.root)
            }
            TYPE_HEADER -> {
                val binding = FragmentRecyclerItemHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HeaderViewHolder(binding.root)
            }
            else -> {
                val binding = FragmentRecyclerItemMarsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                MarsViewHolder(binding.root)
            }
        }
    }

    fun appendItem() {
        listData.add(generateData())
        //notifyDataSetChanged()
        notifyItemInserted(listData.size - 1)
    }

    fun generateData() = Pair(Data("Mars", type = TYPE_MARS), false)

    inner class MarsViewHolder(view: View) : BaseViewHolder(view),
            ItemTouchHelperViewHolder {

        override fun bind(data: Pair<Data, Boolean>) {
            FragmentRecyclerItemMarsBinding.bind(itemView).apply {
                tvName.text = data.first.name
                ivMars.setOnClickListener {
                    onClickItemListener.onItemClick(data.first)
                }
                addItemImageView.setOnClickListener {
                    listData.add(layoutPosition, generateData())
                    notifyItemInserted(layoutPosition)
                }
                removeItemImageView.setOnClickListener {
                    listData.removeAt(layoutPosition)
                    notifyItemRemoved(layoutPosition)
                }
                moveItemUp.setOnClickListener {
                    if (layoutPosition == 1) {
                        listData.removeAt(layoutPosition).apply {
                            listData.add(layoutPosition + 0, this)
                            notifyItemMoved(layoutPosition, layoutPosition + 0)
                        }
                    } else {
                        listData.removeAt(layoutPosition).apply {
                            listData.add(layoutPosition - 1, this)
                            notifyItemMoved(layoutPosition, layoutPosition - 1)
                        }
                    }
                }

                moveItemDown.setOnClickListener {

                    listData.removeAt(layoutPosition).apply {
                        listData.add(layoutPosition + 1, this)
                    }
                    notifyItemMoved(layoutPosition, layoutPosition + 1)
                }
                marsDescriptionTextView.visibility = if (listData[layoutPosition].second) View.VISIBLE else View.GONE
                itemView.setOnClickListener {
                    listData[layoutPosition] = listData[layoutPosition].let {
                        it.first to !it.second
                    }
                    notifyItemChanged(layoutPosition)
                }

                dragHandleImageView.setOnTouchListener { v, event ->
                    if(MotionEventCompat.getActionMasked(event)==MotionEvent.ACTION_DOWN){
                        onStartDragListener.onStartDrag(this@MarsViewHolder)
                    }
                    false
                }
            }
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.GRAY)
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }


    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    //Так как разные типы переопределяем функцию getItemViewType
    override fun getItemViewType(position: Int): Int {
        return listData[position].first.type
    }

    override fun getItemCount() = listData.size

    inner class EarthViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(data: Pair<Data, Boolean>) {
            FragmenRecyclerItemEarthBinding.bind(itemView).apply {
                tvName.text = data.first.name
                tvDescription.text = data.first.description
                ivEarth.setOnClickListener {
                    onClickItemListener.onItemClick(data.first)
                }
            }
        }
    }


    inner class HeaderViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(data: Pair<Data, Boolean>) {
            FragmentRecyclerItemHeaderBinding.bind(itemView).apply {
                tvName.text = data.first.name
                onClickItemListener.onItemClick(data.first)
            }
        }
    }


    abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(data: Pair<Data, Boolean>)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        listData.removeAt(fromPosition).apply {
            listData.add(toPosition, this)
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        listData.removeAt(position)
        notifyItemRemoved(position)
    }
}


