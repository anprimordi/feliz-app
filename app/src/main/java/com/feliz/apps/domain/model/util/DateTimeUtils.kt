package com.feliz.apps.domain.model.util

import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {
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
    private val MONTH_OF_YEAR_FORMATTER = SimpleDateFormat("MMM", idnLocale)
    private val DAY_OF_MONTH_FORMATTER = SimpleDateFormat("dd", idnLocale)
    private val DAY_DATE_MONTH_FORMATTER = SimpleDateFormat("dd MMM", idnLocale)

    //region Helper
    fun isSameDay(date1: Date, date2: Date): Boolean {
        val c1 = Calendar.getInstance().apply { time = date1 }
        val c2 = Calendar.getInstance().apply { time = date2 }
        return c1[Calendar.YEAR] == c2[Calendar.YEAR] && c1[Calendar.DAY_OF_YEAR] == c2[Calendar.DAY_OF_YEAR]
    }

    fun isSameMonthYear(date1: Date, date2: Date): Boolean {
        val c1 = Calendar.getInstance().apply { time = date1 }
        val c2 = Calendar.getInstance().apply { time = date2 }
        return c1[Calendar.YEAR] == c2[Calendar.YEAR] && c1[Calendar.MONTH] == c2[Calendar.MONTH]
    }

    fun isBetweenRange(date: Date, dateStart: Date, dateEnd: Date): Boolean {
        val c = Calendar.getInstance().apply { time = date }
        val cStart = Calendar.getInstance().apply {
            time = dateStart
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 1)
        }
        val cEnd = Calendar.getInstance().apply {
            time = dateEnd
            set(Calendar.HOUR_OF_DAY, 23)
            set(Calendar.MINUTE, 59)
        }
        return c.after(cStart) && c.before(cEnd)
    }
    //endregion Helper

    //region Formatter
    fun formatServerDate(date: Date): String {
        return SERVER_DATE_FORMATTER.format(date)
    }

    fun formatServerDate(millis: Long): String {
        return SERVER_DATE_FORMATTER.format(Date(millis))
    }

    fun formatServerDateTime(date: Date): String {
        return SERVER_DATE_TIME_FORMATTER.format(date)
    }

    fun formatServerDateTime(millis: Long): String {
        return SERVER_DATE_TIME_FORMATTER.format(Date(millis))
    }

    @JvmStatic
    fun formatDate(date: Date?): String {
        return DATE_FORMATTER.format(date ?: Date())
    }

    @JvmStatic
    fun formatDate(millis: Long?): String {
        return DATE_FORMATTER.format(if (millis != null) Date(millis) else Date())
    }

    @JvmStatic
    fun formatDateTime(date: Date?): String {
        return DATE_TIME_FORMATTER.format(date ?: Date())
    }

    @JvmStatic
    fun formatDateTime(millis: Long?): String {
        return DATE_TIME_FORMATTER.format(if (millis != null) Date(millis) else Date())
    }

    @JvmStatic
    fun formatDayDateTime(date: Date): String {
        return DAY_DATE_TIME_FORMATTER.format(date)
    }

    @JvmStatic
    fun formatDayDateTime(millis: Long): String {
        return DAY_DATE_TIME_FORMATTER.format(Date(millis))
    }

    @JvmStatic
    fun formatDayDate(date: Date): String {
        return DAY_DATE_FORMATTER.format(date)
    }

    @JvmStatic
    fun formatDayDate(millis: Long): String {
        return DAY_DATE_FORMATTER.format(Date(millis))
    }

    @JvmStatic
    fun formatTime(date: Date?): String {
        return TIME_FORMATTER.format(date ?: Date())
    }

    @JvmStatic
    fun formatTime(millis: Long?): String {
        return TIME_FORMATTER.format(if (millis != null) Date(millis) else Date())
    }

    @JvmStatic
    fun formatMonthYear(date: Date?): String {
        return MONTH_YEAR_FORMATTER.format(date ?: Date())
    }

    @JvmStatic
    fun formatMonthYear(millis: Long?): String {
        return MONTH_YEAR_FORMATTER.format(if (millis != null) Date(millis) else Date())
    }

    @JvmStatic
    fun formatDayDateMonth(date: Date?): String {
        return DAY_DATE_MONTH_FORMATTER.format(date ?: Date())
    }

    @JvmStatic
    fun formatDayDateMonth(millis: Long?): String {
        return DAY_DATE_MONTH_FORMATTER.format(if (millis != null) Date(millis) else Date())
    }

    @JvmStatic
    fun formatDayOfMonth(date: Date?): String {
        return DAY_OF_MONTH_FORMATTER.format(date ?: Date())
    }

    @JvmStatic
    fun formatDayOfMonth(millis: Long?): String {
        return DAY_OF_MONTH_FORMATTER.format(if (millis != null) Date(millis) else Date())
    }

    @JvmStatic
    fun formatMonthOfYear(date: Date?): String {
        return MONTH_OF_YEAR_FORMATTER.format(date ?: Date())
    }

    @JvmStatic
    fun formatMonthOfYear(millis: Long?): String {
        return MONTH_OF_YEAR_FORMATTER.format(if (millis != null) Date(millis) else Date())
    }

    //endregion Formatter

    //region Parser
    fun parseServerDate(dateString: String?): Date? {
        return try {
            SERVER_DATE_TIME_FORMATTER.parse(dateString ?: throw Exception()) ?: throw Exception()
        } catch (ex: Exception) {
            try {
                SERVER_DATE_FORMATTER.parse(dateString ?: throw Exception()) ?: throw Exception()
            } catch (ex: Exception) {
                null
            }
        }
    }
    //endregion Parser

}