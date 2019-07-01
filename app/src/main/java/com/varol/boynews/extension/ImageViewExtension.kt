package com.varol.boynews.extension

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.varol.boynews.R
import com.varol.boynews.util.GlideApp

fun ImageView.setImageByUrl(url: String?) {
    if (url?.isNotEmpty() == true) {

        val circularProgressDrawable = CircularProgressDrawable(this.context)
        circularProgressDrawable.apply {
            strokeWidth = 10f
            centerRadius = 50f
            start()
        }

        GlideApp.with(this.context)
            .load(url)
            .placeholder(circularProgressDrawable)
            .error(R.drawable.ic_placeholder)
            .into(this)
    } else {
        GlideApp.with(this.context)
            .load(R.drawable.ic_placeholder)
            .into(this)
    }
}