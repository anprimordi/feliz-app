package com.feliz.apps.presentation.ui.vendor.detail

import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.feliz.apps.R
import com.feliz.apps.databinding.ItemBannerMediaBinding
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder

class VendorDetailBannerAdapter : BaseBannerAdapter<String>() {

    override fun bindData(
        holder: BaseViewHolder<String>?,
        data: String?,
        position: Int,
        pageSize: Int
    ) {
        val binding = DataBindingUtil.bind<ItemBannerMediaBinding>(holder!!.itemView)

        Glide.with(binding!!.root.context)
            .load(data)
            .centerCrop()
            .into(binding.imageMedia)

    }

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.item_banner_media
    }
}