package com.varol.boynews.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SourceModel(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: Int?
) : Parcelable