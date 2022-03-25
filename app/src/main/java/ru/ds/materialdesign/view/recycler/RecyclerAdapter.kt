package ru.ds.materialdesign.view.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.ds.materialdesign.databinding.FragmenRecyclerItemEarthBinding
import ru.ds.materialdesign.databinding.FragmentRecyclerItemMarsBinding

class RecyclerAdapter(val onClickItemListener: OnClickItemListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var listData: List<Data>
    fun setData(listData: List<Data>) {
        this.listData = listData
    }

    //заполняем список в зависимости от типа шаблона
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_EARTH -> {
                val binding = FragmenRecyclerItemEarthBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                EarthViewHolder(binding.root)
            }
            else -> {
                val binding = FragmentRecyclerItemMarsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                MarsViewHolder(binding.root)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_EARTH -> {
                (holder as EarthViewHolder).bind(listData[position])
            }
            else -> {
                (holder as MarsViewHolder).bind(listData[position])
            }
        }
    }

    override fun getItemCount() = listData.size

    inner class EarthViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: Data) {
            FragmenRecyclerItemEarthBinding.bind(itemView).apply {
                tvName.text = data.name
                tvDescription.text = data.description
                ivEarth.setOnClickListener {
                    onClickItemListener.onItemClick(data)
                }
            }
        }
    }

    inner class MarsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: Data) {
            FragmentRecyclerItemMarsBinding.bind(itemView).apply {
                tvName.text = data.name
                ivMars.setOnClickListener {
                onClickItemListener.onItemClick(data)
                }
            }
        }
    }

    //Так как разные типы переопределяем функцию getItemViewType
    override fun getItemViewType(position: Int): Int {
        return listData[position].type
    }

}