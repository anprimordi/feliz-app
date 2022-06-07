package com.feliz.apps.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.feliz.apps.databinding.ItemHomeOurProductBinding
import com.feliz.apps.domain.model.ProductCategory
import com.feliz.apps.presentation.ui.home.adapter.HomeOurProductListAdapter.ViewHolder

class HomeOurProductListAdapter(
    private val actionClicked: (item: ProductCategory) -> Unit
) : ListAdapter<ProductCategory, ViewHolder>(ItemComparator) {

    private object ItemComparator : DiffUtil.ItemCallback<ProductCategory>() {
        override fun areItemsTheSame(oldItem: ProductCategory, newItem: ProductCategory): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ProductCategory,
            newItem: ProductCategory
        ): Boolean {
            return oldItem == newItem
        }

    }

    class ViewHolder(val binding: ItemHomeOurProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemHomeOurProductBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        // fetch thumbnail
        Glide.with(holder.binding.root.context)
            .load(item.photoUrl)
            .centerCrop()
            .into(holder.binding.imageThumbnail)
        holder.binding.textName.text = item.name
        holder.binding.root.setOnClickListener { actionClicked(item) }
    }


}