package com.feliz.apps.domain.model.util

import java.text.NumberFormat
import java.util.*

object CurrencyUtils {
    private val idnLocale = Locale("in", "ID")

    private val IDR_FORMAT = NumberFormat.getCurrencyInstance(idnLocale)

    @JvmStatic
    fun formatIdr(price: Long): String {
        return IDR_FORMAT.format(price)
    }
}