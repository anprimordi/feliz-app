package com.feliz.apps.presentation.ui.vendor.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.feliz.apps.databinding.ItemVendorDetailBinding
import com.feliz.apps.domain.model.Vendor
import com.feliz.apps.presentation.ui.vendor.detail.VendorDetailListAdapter.ViewHolder

class VendorDetailListAdapter(
    val categoryName: String,
    val actionClicked: (item: Vendor) -> Unit
) :
    ListAdapter<Vendor, ViewHolder>(ItemComparator) {
    object ItemComparator : DiffUtil.ItemCallback<Vendor>() {
        override fun areItemsTheSame(oldItem: Vendor, newItem: Vendor): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Vendor, newItem: Vendor): Boolean {
            return oldItem == newItem
        }

    }

    class ViewHolder(val binding: ItemVendorDetailBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemVendorDetailBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        Glide.with(holder.binding.root.context)
            .load(item.thumbnail.url)
            .into(holder.binding.imageThumbnail)
        holder.binding.textCategory.text = categoryName
        holder.binding.textName.text = item.name
        holder.binding.textAddress.text = item.address
        holder.binding.root.setOnClickListener { actionClicked(item) }
    }
}