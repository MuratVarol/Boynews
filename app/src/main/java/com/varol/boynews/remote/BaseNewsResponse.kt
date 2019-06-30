package com.varol.boynews.remote

import com.google.gson.annotations.SerializedName


data class BaseNewsResponse<T>(

    @SerializedName("status")
    val status: String?,

    @SerializedName("totalResults")
    val totalResults: Int?,

    @SerializedName("articles")
    val articles: T

)