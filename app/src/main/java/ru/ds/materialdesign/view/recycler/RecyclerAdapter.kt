package ru.ds.materialdesign.view.recycler

import android.provider.Settings.Global.getInt
import android.provider.Settings.Global.getString
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ru.ds.materialdesign.R
import ru.ds.materialdesign.databinding.FragmenRecyclerItemEarthBinding
import ru.ds.materialdesign.databinding.FragmentRecyclerItemHeaderBinding
import ru.ds.materialdesign.databinding.FragmentRecyclerItemMarsBinding

class RecyclerAdapter(val onClickItemListener: OnClickItemListener) : RecyclerView.Adapter<RecyclerAdapter.BaseViewHolder>() {

    private lateinit var listData: MutableList<Data>
    fun setData(listData: MutableList<Data>) {
        this.listData = listData
    }

    //заполняем список в зависимости от типа шаблона
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            TYPE_EARTH -> {
                val binding = FragmenRecyclerItemEarthBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        notifyItemInserted(listData.size-1)
    }
    fun generateData() = Data("Mars", type=TYPE_MARS)

    inner class MarsViewHolder(view:View):BaseViewHolder(view){
        override fun bind(data: Data){
            FragmentRecyclerItemMarsBinding.bind(itemView).apply {
                tvName.text = data.name
                ivMars.setOnClickListener {
                    onClickItemListener.onItemClick(data)
                }
                addItemImageView.setOnClickListener {
                    listData.add(layoutPosition,generateData())
                    notifyItemInserted(layoutPosition)
                }
                removeItemImageView.setOnClickListener {
                    listData.removeAt(layoutPosition)
                    notifyItemRemoved(layoutPosition)
                }
                moveItemUp.setOnClickListener {
                    if (getLayoutPosition() == 1) {
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
                        listData.add(layoutPosition+1,this)
                    }
                    notifyItemMoved(layoutPosition,layoutPosition+1)
                }
            }
        }
    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    //Так как разные типы переопределяем функцию getItemViewType
    override fun getItemViewType(position: Int): Int {
        return listData[position].type
    }

    override fun getItemCount() = listData.size

    inner class EarthViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(data: Data) {
            FragmenRecyclerItemEarthBinding.bind(itemView).apply {
                tvName.text = data.name
                tvDescription.text = data.description
                ivEarth.setOnClickListener {
                    onClickItemListener.onItemClick(data)
                }
            }
        }
    }


    inner class HeaderViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(data: Data) {
            FragmentRecyclerItemHeaderBinding.bind(itemView).apply {
                tvName.text = data.name
                onClickItemListener.onItemClick(data)
            }
        }
    }


    abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(data: Data)
    }
}


