package com.feliz.apps.domain.model.common

import com.feliz.apps.domain.model.util.DateTimeUtils
import java.text.SimpleDateFormat
import java.util.*

object Constants {

//    TIME DEFAULT

    const val DEFAULT_SERVER_DATE = "2020-01-01"
    const val DEFAULT_SERVER_DATE_TIME = "2021-01-01 00:00:00"

    private val idnLocale = Locale("in", "ID")

    private val SERVER_DATE_FORMATTER = SimpleDateFormat("yyyy-MM-dd", idnLocale)
    private val SERVER_DATE_TIME_FORMATTER = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", idnLocale)

    private val DAY_DATE_FORMATTER = SimpleDateFormat("EEEE, d MMMM yyyy", idnLocale)
    private val DAY_DATE_TIME_FORMATTER = SimpleDateFormat("EEEE, d MMMM yyyy HH:mm", idnLocale)
    private val DATE_FORMATTER = SimpleDateFormat("d MMMM yyyy", idnLocale)
    private val DATE_TIME_FORMATTER = SimpleDateFormat("d MMMM yyyy HH:mm", idnLocale)
    private val MONTH_YEAR_FORMATTER = SimpleDateFormat("MMMM yyyy", idnLocale)
    private val TIME_FORMATTER = SimpleDateFormat("HH:mm", idnLocale)

//    MODEL DEFAULT

    const val DEFAULT_ID: Long = -1
    const val DEFAULT_UID: String = ""
    const val DEFAULT_TOKEN: String = ""
    val DEFAULT_DATE: Date =
        DateTimeUtils.parseServerDate(DateTimeUtils.DEFAULT_SERVER_DATE_TIME) ?: Date()
    const val DEFAULT_PHOTO_URL: String = ""
    const val DEFAULT_NAME: String = "-"
    const val DEFAULT_EMAIL: String = "-"
    const val DEFAULT_PASSWORD: String = "1234"
    const val DEFAULT_PHONE: Long = 0
    const val DEFAULT_ADDRESS: String = "-"
    const val DEFAULT_FACILITY: String = "-"
    const val DEFAULT_LOCATION_SPACE: String = "-"
    const val DEFAULT_CAPACITY: String = "0"
    const val DEFAULT_ROOM: String = "0"
    const val DEFAULT_COORDINATE: Double = 0.0
    const val DEFAULT_VEHICLE_PLATE_NUMBER: String = "-"
    const val DEFAULT_URL: String = "-"
    const val DEFAULT_SCORE: Int = 0
    const val DEFAULT_REVIEW: String = "-"
    const val DEFAULT_NOMINAL: Long = 0
    const val DEFAULT_DISTANCE: Long = 0
    const val DEFAULT_COUNT: Int = 0
    const val DEFAULT_DESCRIPTION: String = "-"
    const val DEFAULT_PRICE: Long = 0
}