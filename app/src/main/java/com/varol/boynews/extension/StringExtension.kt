package com.varol.boynews.extension

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

/**
 * Converts String to Date object
 * If invalid string given; current time will be return
 */
@SuppressLint("SimpleDateFormat")
fun String.toDate(format: String = "yyyy-MM-dd'T'HH:mm:ss"): Date {
    val df = SimpleDateFormat(format)


    return try {
        df.parse(this)
    } catch (ex: Exception) {
        Calendar.getInstance().time
    }
}

/**
 * Returns HOUR, MINUTE and SECOND of given time
 */
fun String.getDayTime(format: String = "yyyy-MM-dd'T'HH:mm:ss"): String {
    val calendar = Calendar.getInstance()
    calendar.time = this.toDate(format)

    return "${String.format("%02d", calendar.get(Calendar.HOUR))} : " +
            "${String.format("%02d", calendar.get(Calendar.MINUTE))} : " +
            "${String.format("%02d", calendar.get(Calendar.SECOND))} "
}

fun String.dateToTimeStamp(format: String = "yyyy-MM-dd'T'HH:mm:ss"): Long? {

    return try {
        this.toDate(format).time
    } catch (e: java.lang.Exception) {
        null
    }
}
