package com.feliz.apps.presentation.ui.home.companyprofile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.feliz.apps.R
import com.feliz.apps.databinding.ItemCompanyTeamBinding
import com.feliz.apps.domain.model.CompanyProfile.Team
import com.feliz.apps.presentation.ui.home.companyprofile.CompanyProfileTeamListAdapter.ViewHolder

class CompanyProfileTeamListAdapter : ListAdapter<Team, ViewHolder>(ItemComparator) {
    private object ItemComparator : DiffUtil.ItemCallback<Team>() {
        override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(val binding: ItemCompanyTeamBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCompanyTeamBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.binding.root.context
        val item = getItem(position)

        holder.binding.item = item
        holder.binding.apply {
            Glide.with(root.context)
                .load(item.photoUrl)
                .into(imageThumbnail)

            textFullName.text =
                context.getString(R.string.text_company_team_name, item.fullName, item.nickname)
            textPosition.text = item.position
        }
    }
}