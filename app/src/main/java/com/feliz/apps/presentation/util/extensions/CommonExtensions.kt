package com.feliz.apps.presentation.util.extensions

import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

fun Boolean.toInt() = if (this) 1 else 0

fun <T> ArrayList<T>.freshInsert(data: List<T>) {
    clear()
    addAll(data)
}

// LiveData
fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }

fun getRandomString(length: Int = Random.nextInt(from = 8, until = 24)): String {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}