package com.feliz.apps.presentation.ui.home.adapter

import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.feliz.apps.R
import com.feliz.apps.databinding.ItemBannerHomeMediaBinding
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder

class HomeBannerViewPagerAdapter : BaseBannerAdapter<String>() {

    override fun bindData(
        holder: BaseViewHolder<String>?,
        data: String?,
        position: Int,
        pageSize: Int
    ) {
        val binding = DataBindingUtil.bind<ItemBannerHomeMediaBinding>(holder!!.itemView)
        Glide.with(binding!!.root.context)
            .load(data)
            .centerCrop()
            .into(binding.imageMedia)
    }

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.item_banner_home_media
    }
}