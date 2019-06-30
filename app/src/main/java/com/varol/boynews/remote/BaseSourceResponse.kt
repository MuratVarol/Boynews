package com.varol.boynews.remote

import com.google.gson.annotations.SerializedName


data class BaseSourceResponse<T>(

    @SerializedName("status")
    val status: String?,

    @SerializedName("sources")
    val sources: T

)