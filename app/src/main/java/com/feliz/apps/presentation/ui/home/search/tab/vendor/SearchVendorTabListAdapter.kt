package com.feliz.apps.presentation.ui.home.search.tab.vendor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.feliz.apps.databinding.ItemSearchVendorBinding
import com.feliz.apps.domain.model.Vendor
import com.feliz.apps.presentation.ui.home.search.tab.vendor.SearchVendorTabListAdapter.ViewHolder

class SearchVendorTabListAdapter(private val actionClicked: (item: Vendor) -> Unit) :
    ListAdapter<Vendor, ViewHolder>(ItemComparator) {
    object ItemComparator : DiffUtil.ItemCallback<Vendor>() {
        override fun areItemsTheSame(oldItem: Vendor, newItem: Vendor): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Vendor, newItem: Vendor): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(val binding: ItemSearchVendorBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemSearchVendorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.item = item
        Glide.with(holder.binding.root.context)
            .load(item.thumbnail.url)
            .into(holder.binding.imageThumbnail)
//        holder.binding.textCategory.text = item.category.name
        holder.binding.textName.text = item.name
        holder.binding.textAddress.text = item.address
        holder.binding.root.setOnClickListener { actionClicked(item) }
    }
}