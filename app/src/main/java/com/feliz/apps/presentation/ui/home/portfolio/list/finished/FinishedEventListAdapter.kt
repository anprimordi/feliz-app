package com.feliz.apps.presentation.ui.home.portfolio.list.finished

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.feliz.apps.databinding.ItemSearchPortfolioBinding
import com.feliz.apps.domain.model.Portfolio
import com.feliz.apps.domain.model.util.DateTimeUtils
import com.feliz.apps.presentation.ui.home.portfolio.list.finished.FinishedEventListAdapter.ViewHolder
import com.feliz.apps.presentation.util.diffutil.ModelDiffUtil

class FinishedEventListAdapter(
    val actionClicked: (item: Portfolio) -> Unit
) : ListAdapter<Portfolio, ViewHolder>(ModelDiffUtil<Portfolio>()) {

    class ViewHolder(val binding: ItemSearchPortfolioBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemSearchPortfolioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.binding.apply {
            Glide.with(root.context)
                .load(item.photoUrl)
                .into(imageThumbnail)

            textName.text = item.name
            textAddress.text = item.location
            textDateDay.text = DateTimeUtils.formatDayOfMonth(item.dateTime)
            textDateMonth.text = DateTimeUtils.formatMonthOfYear(item.dateTime)
            root.setOnClickListener { actionClicked(item) }
        }
    }
}