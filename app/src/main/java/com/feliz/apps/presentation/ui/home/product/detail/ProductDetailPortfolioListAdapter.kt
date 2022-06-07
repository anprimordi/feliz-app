package com.feliz.apps.presentation.ui.home.product.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.feliz.apps.databinding.ItemProductPortfolioBinding
import com.feliz.apps.domain.model.Portfolio
import com.feliz.apps.presentation.ui.home.product.detail.ProductDetailPortfolioListAdapter.ViewHolder

class ProductDetailPortfolioListAdapter(
    private val productCategoryName: String,
    private val actionClicked: (item: Portfolio) -> Unit
) : ListAdapter<Portfolio, ViewHolder>(ItemComparator) {
    private object ItemComparator : DiffUtil.ItemCallback<Portfolio>() {
        override fun areItemsTheSame(oldItem: Portfolio, newItem: Portfolio): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Portfolio, newItem: Portfolio): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(val binding: ItemProductPortfolioBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProductPortfolioBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.binding.item = item
        Glide.with(holder.binding.root.context)
            .load(item.photoUrl)
            .centerCrop()
            .into(holder.binding.imageThumbnail)
        holder.binding.apply {
            textName.text = item.name
            textLocation.text = item.location
            textProduct.text = productCategoryName
            root.setOnClickListener { actionClicked(item) }
        }
    }
}