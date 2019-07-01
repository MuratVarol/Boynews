package com.varol.boynews.extension

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*


@SuppressLint("SimpleDateFormat")
fun String.toDate(format: String = "yyyy-MM-dd'T'HH:mm:ssZ"): Date {
    val df = SimpleDateFormat(format)


    return try {
        return df.parse(this)
    } catch (ex: Exception) {
        Calendar.getInstance().time
    }
}

fun String.getHoursOnDate(format: String = "yyyy-MM-dd'T'HH:mm:ssZ"): String {
    val calendar = Calendar.getInstance()
    calendar.time = this.toDate(format)

    return "${calendar.get(Calendar.HOUR)} : " +
            "${calendar.get(Calendar.MINUTE)} : " +
            "${calendar.get(Calendar.SECOND)} "


}
