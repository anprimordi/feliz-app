package com.feliz.apps.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.feliz.apps.databinding.ItemPortfolioTabBinding
import com.feliz.apps.domain.model.Portfolio
import com.feliz.apps.domain.model.util.DateTimeUtils
import com.feliz.apps.presentation.ui.home.adapter.HomePortfolioListAdapter.ViewHolder

class HomePortfolioListAdapter(
    private val actionClicked: (item: Portfolio) -> Unit
) : ListAdapter<Portfolio, ViewHolder>(ItemComparator) {

    class ViewHolder(val binding: ItemPortfolioTabBinding) :
        RecyclerView.ViewHolder(binding.root)

    private object ItemComparator : DiffUtil.ItemCallback<Portfolio>() {
        override fun areItemsTheSame(oldItem: Portfolio, newItem: Portfolio): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Portfolio, newItem: Portfolio): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemPortfolioTabBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        /* val dateLong = DateTimeUtils.formatDate(item.dateTime)
         val formatter = SimpleDateFormat("dd MMM", Locale.getDefault())
         val date = formatter.format(dateLong)*/

        Glide.with(holder.binding.root.context)
            .load(item.photoUrl)
            .centerCrop()
            .into(holder.binding.imageThumbnail)
        holder.binding.textName.text = item.name
        holder.binding.textAddress.text = item.location
        holder.binding.textDateDay.text = DateTimeUtils.formatDayOfMonth(item.dateTime)
        holder.binding.textDateMonth.text = DateTimeUtils.formatMonthOfYear(item.dateTime)
        holder.binding.root.setOnClickListener { actionClicked(item) }
    }

}