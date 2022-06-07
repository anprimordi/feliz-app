package com.feliz.apps.presentation.ui.form.edit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.feliz.apps.databinding.ItemEditVendorBinding
import com.feliz.apps.presentation.ui.form.edit.FormEditAdapter.ViewHolder

class FormEditAdapter(val actionDelete: (data: Pair<String, String>) -> Unit) :
    ListAdapter<Pair<String, String>, ViewHolder>(ItemComparator) {
    object ItemComparator : DiffUtil.ItemCallback<Pair<String, String>>() {
        override fun areItemsTheSame(
            oldItem: Pair<String, String>,
            newItem: Pair<String, String>
        ): Boolean {
            return oldItem.first == newItem.first
        }

        override fun areContentsTheSame(
            oldItem: Pair<String, String>,
            newItem: Pair<String, String>
        ): Boolean {
            return oldItem == newItem
        }

    }

    class ViewHolder(val binding: ItemEditVendorBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemEditVendorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.binding.apply {
            name = item.first
            desc = item.second
            textDelete.setOnClickListener {
                actionDelete(item)
            }
        }
    }
}