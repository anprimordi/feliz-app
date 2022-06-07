package com.feliz.apps.domain.model.common

interface Model {
    fun contains(query: String): Boolean = false
    fun isItemSameWith(value: Model): Boolean = false
    fun isContentSameWith(value: Model): Boolean = false
}