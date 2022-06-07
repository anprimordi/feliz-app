package com.feliz.apps.presentation.ui.home.search.tab.finishedevent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.feliz.apps.databinding.ItemSearchPortfolioBinding
import com.feliz.apps.domain.model.Portfolio
import com.feliz.apps.domain.model.util.DateTimeUtils
import com.feliz.apps.presentation.ui.home.search.tab.finishedevent.SearchFinishedEventTabListAdapter.ViewHolder

class SearchFinishedEventTabListAdapter(val actionClicked: (item: Portfolio) -> Unit) :
    ListAdapter<Portfolio, ViewHolder>(ItemComparator) {
    object ItemComparator : DiffUtil.ItemCallback<Portfolio>() {
        override fun areItemsTheSame(oldItem: Portfolio, newItem: Portfolio): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Portfolio, newItem: Portfolio): Boolean {
            return oldItem == newItem
        }

    }

    class ViewHolder(val binding: ItemSearchPortfolioBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemSearchPortfolioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.binding.item = item
        Glide.with(holder.binding.root.context)
            .load(item.photoUrl)
            .into(holder.binding.imageThumbnail)

        holder.binding.textName.text = item.name
        holder.binding.textAddress.text = item.location
        holder.binding.textDateDay.text = DateTimeUtils.formatDayOfMonth(item.dateTime)
        holder.binding.textDateMonth.text = DateTimeUtils.formatMonthOfYear(item.dateTime)
        holder.binding.root.setOnClickListener { actionClicked(item) }
    }
}