package com.example.classwork_9.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.classwork_9.model.Indicator
import com.example.classwork_9.databinding.ItemIndicatorBinding

class IndicatorAdapter : ListAdapter<Indicator, IndicatorAdapter.IndicatorViewHolder>(IndicatorItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = IndicatorViewHolder(
        ItemIndicatorBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: IndicatorViewHolder, position: Int) {
        holder.bind()
    }

    inner class IndicatorViewHolder(private val binding: ItemIndicatorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.root.apply {
                if (getItem(adapterPosition).number != -1) {
                    backgroundTintList = ColorStateList.valueOf(Color.GREEN)
                } else {
                    backgroundTintList = ColorStateList.valueOf(Color.GRAY)
                }
            }
        }
    }

    private object IndicatorItemCallback : DiffUtil.ItemCallback<Indicator>() {
        override fun areItemsTheSame(oldItem: Indicator, newItem: Indicator): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Indicator, newItem: Indicator): Boolean {
            return oldItem == newItem
        }
    }

}