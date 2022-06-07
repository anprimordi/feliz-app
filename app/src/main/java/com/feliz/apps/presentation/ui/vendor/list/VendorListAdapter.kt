package com.feliz.apps.presentation.ui.vendor.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.feliz.apps.databinding.ItemVendorListBinding
import com.feliz.apps.domain.model.Vendor
import com.feliz.apps.presentation.ui.vendor.list.VendorListAdapter.ViewHolder

class VendorListAdapter(private val actionClicked: (vendor: Vendor) -> Unit) :
    ListAdapter<Vendor, ViewHolder>(ItemComparator) {
    object ItemComparator : DiffUtil.ItemCallback<Vendor>() {
        override fun areItemsTheSame(oldItem: Vendor, newItem: Vendor): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Vendor, newItem: Vendor): Boolean {
            return oldItem == newItem
        }

    }

    class ViewHolder(val binding: ItemVendorListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemVendorListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.binding.item = item
        holder.binding.apply {
            Glide.with(root.context)
                .load(item.thumbnail.url)
                .into(imageThumbnail)
            textName.text = item.name
            textAddress.text = item.address
            root.setOnClickListener { actionClicked(item) }
        }
    }
}