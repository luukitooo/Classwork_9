package com.example.classwork_9.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.classwork_9.model.ButtonActions
import com.example.classwork_9.model.ButtonTypes
import com.example.classwork_9.databinding.ItemButtonNumericBinding
import com.example.classwork_9.databinding.ItemButtonOtherBinding

class ButtonAdapter : ListAdapter<ButtonTypes, RecyclerView.ViewHolder>(ButtonItemCallback) {

    private companion object {
        const val BTN_NUMERIC = 1
        const val BTN_OTHER = 2
    }

    var onNumericClickListener: ((ButtonTypes.Numeric) -> Unit)? = null

    var onRemoveClickListener: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            BTN_NUMERIC -> NumericViewHolder(
                ItemButtonNumericBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> OtherViewHolder(
                ItemButtonOtherBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NumericViewHolder -> holder.bind()
            is OtherViewHolder -> holder.bind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ButtonTypes.Numeric -> BTN_NUMERIC
            else -> BTN_OTHER
        }
    }

    inner class NumericViewHolder(private val binding: ItemButtonNumericBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val numeric = getItem(adapterPosition) as ButtonTypes.Numeric
            binding.root.apply {
                text = numeric.number.toString()
                setOnClickListener {
                    onNumericClickListener?.invoke(numeric)
                }
            }
        }
    }

    inner class OtherViewHolder(private val binding: ItemButtonOtherBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val other = getItem(adapterPosition) as ButtonTypes.Other
            binding.root.apply {
                when (other.action) {
                    ButtonActions.FINGERPRINT -> {
                        setImageResource(other.icon)
                    }
                    ButtonActions.REMOVE -> {
                        setImageResource(other.icon)
                        setOnClickListener {
                            onRemoveClickListener?.invoke()
                        }
                    }
                }
            }
        }
    }

    private object ButtonItemCallback : DiffUtil.ItemCallback<ButtonTypes>() {
        override fun areItemsTheSame(oldItem: ButtonTypes, newItem: ButtonTypes): Boolean {
            return false
        }

        override fun areContentsTheSame(oldItem: ButtonTypes, newItem: ButtonTypes): Boolean {
            return false
        }
    }

}