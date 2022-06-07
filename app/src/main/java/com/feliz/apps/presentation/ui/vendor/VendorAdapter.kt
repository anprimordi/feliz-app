package com.feliz.apps.presentation.ui.vendor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.feliz.apps.databinding.ItemVendorCategoryBinding
import com.feliz.apps.domain.model.VendorCategory
import com.feliz.apps.presentation.ui.vendor.VendorAdapter.ViewHolder

class VendorAdapter(val actionClicked: (item: VendorCategory) -> Unit) :
    ListAdapter<VendorCategory, ViewHolder>(ItemComparator) {
    private object ItemComparator : DiffUtil.ItemCallback<VendorCategory>() {
        override fun areItemsTheSame(oldItem: VendorCategory, newItem: VendorCategory): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: VendorCategory, newItem: VendorCategory): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(val binding: ItemVendorCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemVendorCategoryBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.binding.apply {
            Glide.with(root.context)
                .load(item.photoUrl)
                .centerCrop()
                .into(imageThumbnail)
            textName.text = item.name
            root.setOnClickListener { actionClicked(item) }
        }
    }
}