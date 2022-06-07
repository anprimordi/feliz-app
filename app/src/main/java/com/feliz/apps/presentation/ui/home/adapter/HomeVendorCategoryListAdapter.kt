package com.feliz.apps.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.feliz.apps.R
import com.feliz.apps.databinding.ItemHomeVendorCategoryBinding
import com.feliz.apps.domain.model.Vendor
import com.feliz.apps.domain.model.VendorCategory
import com.feliz.apps.presentation.ui.home.adapter.HomeVendorCategoryListAdapter.ViewHolder
import com.feliz.apps.presentation.util.diffutil.ModelDiffUtil

class HomeVendorCategoryListAdapter(
    private val onVendorClicked: (Vendor) -> Unit
) : ListAdapter<VendorCategory, ViewHolder>(ModelDiffUtil<VendorCategory>()) {

    class ViewHolder(val binding: ItemHomeVendorCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_home_vendor_category,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val homeVendorListAdapter = HomeVendorListAdapter { onVendorClicked(it) }

        val item = getItem(position)
        holder.binding.item = item
        holder.binding.textDescription.text =
            holder.binding.root.context.getString(
                R.string.title_home_vendor_category_subtitle,
                item.name
            )
        holder.binding.rvEvent.adapter = homeVendorListAdapter

        homeVendorListAdapter.submitList(item.vendors?.take(5))
    }
}