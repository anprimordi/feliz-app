package com.feliz.apps.presentation.ui.home.search.tab.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.feliz.apps.databinding.ItemSearchProductBinding
import com.feliz.apps.domain.model.Product
import com.feliz.apps.domain.model.util.CurrencyUtils
import com.feliz.apps.presentation.ui.home.search.tab.product.SearchProductTabListAdapter.ViewHolder

class SearchProductTabListAdapter(private val actionClicked: (item: Product) -> Unit) :
    ListAdapter<Product, ViewHolder>(ItemComparator) {

    object ItemComparator : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(val binding: ItemSearchProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemSearchProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.binding.item = item
        Glide.with(holder.binding.root.context)
            .load(item.thumbnailUrl)
            .into(holder.binding.imageMedia)
        holder.binding.textName.text = item.name
        holder.binding.textPrice.text = CurrencyUtils.formatIdr(item.price)
        holder.binding.root.setOnClickListener { actionClicked(item) }
    }
}