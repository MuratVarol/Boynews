package com.varol.boynews.extension

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

/**
 * Converts String to Date object
 * If invalid string given; current time will be return
 */
@SuppressLint("SimpleDateFormat")
fun String.toDate(format: String = "yyyy-MM-dd'T'HH:mm:ssZ"): Date {
    val df = SimpleDateFormat(format)


    return try {
        return df.parse(this)
    } catch (ex: Exception) {
        Calendar.getInstance().time
    }
}

/**
 * Returns HOUR, MINUTE and SECOND of given time
 */
fun String.getDayTime(format: String = "yyyy-MM-dd'T'HH:mm:ssZ"): String {
    val calendar = Calendar.getInstance()
    calendar.time = this.toDate(format)

    return "${calendar.get(Calendar.HOUR)} : " +
            "${calendar.get(Calendar.MINUTE)} : " +
            "${calendar.get(Calendar.SECOND)} "

}
