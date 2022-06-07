package com.feliz.apps.presentation.util.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.feliz.apps.domain.model.common.Model

class ModelDiffUtil<T : Model> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.isItemSameWith(newItem)
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.isContentSameWith(newItem)
    }
}