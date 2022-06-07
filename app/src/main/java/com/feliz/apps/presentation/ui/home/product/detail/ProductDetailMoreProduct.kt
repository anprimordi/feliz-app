package com.feliz.apps.presentation.ui.home.product.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.feliz.apps.databinding.ItemProductMorePriceBinding
import com.feliz.apps.domain.model.Product
import com.feliz.apps.domain.model.util.CurrencyUtils
import com.feliz.apps.presentation.ui.home.product.detail.ProductDetailMoreProduct.ViewHolder
import com.feliz.apps.presentation.util.diffutil.ModelDiffUtil

class ProductDetailMoreProduct(
    private val actionClicked: (item: Product) -> Unit
) : ListAdapter<Product, ViewHolder>(ModelDiffUtil<Product>()) {

    class ViewHolder(val binding: ItemProductMorePriceBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemProductMorePriceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.binding.apply {
            Glide.with(root.context)
                .load(item.thumbnailUrl)
                .centerCrop()
                .into(imageMedia)

            textName.text = item.name
            textPrice.text = CurrencyUtils.formatIdr(item.price)
            root.setOnClickListener { actionClicked(item) }
        }
    }
}