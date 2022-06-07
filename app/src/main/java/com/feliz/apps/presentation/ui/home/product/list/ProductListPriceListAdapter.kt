package com.feliz.apps.presentation.ui.home.product.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.feliz.apps.databinding.ItemProductCategoryPriceListBinding
import com.feliz.apps.domain.model.Product
import com.feliz.apps.domain.model.util.CurrencyUtils
import com.feliz.apps.presentation.ui.home.product.list.ProductListPriceListAdapter.ViewHolder

class ProductListPriceListAdapter(
    private val actionClicked: (item: Product, categoryName: String) -> Unit
) : ListAdapter<Product, ViewHolder>(ItemComparator) {
    private object ItemComparator : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(val binding: ItemProductCategoryPriceListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemProductCategoryPriceListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.binding.item = item
        holder.binding.apply {
            Glide.with(root.context)
                .load(item.thumbnailUrl)
                .centerCrop()
                .into(imageMedia)

            textName.text = item.name
//            textDescription.text = item.detail
            textPrice.text = CurrencyUtils.formatIdr(item.price)
            root.setOnClickListener { actionClicked(item, item.category.name) }
        }
    }
}