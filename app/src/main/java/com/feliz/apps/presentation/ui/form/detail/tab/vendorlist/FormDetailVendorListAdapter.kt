package com.feliz.apps.presentation.ui.form.detail.tab.vendorlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.feliz.apps.R
import com.feliz.apps.databinding.ItemFormDetailBinding
import com.feliz.apps.presentation.ui.form.detail.tab.vendorlist.FormDetailVendorListAdapter.ViewHolder

class FormDetailVendorListAdapter :
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

    class ViewHolder(val binding: ItemFormDetailBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemFormDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.titleNumber.text = holder.binding.root.context.getString(
            R.string.text_vendor_number,
            (position + 1).toString()
        )
        holder.binding.textName.text = item.first
        holder.binding.textDescription.text = item.second
    }
}