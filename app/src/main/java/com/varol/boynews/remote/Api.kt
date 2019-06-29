package com.varol.boynews.remote

import com.varol.boynews.models.NewsModel
import com.varol.boynews.models.SourceModel
import io.reactivex.Single
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {

    @POST("/v2/sources")
    fun getSources(
        @Query(value = "apiKey") apiKey: String
    ): Single<BaseResponse<MutableList<SourceModel>>>

    @POST("v2/top-headlines")
    fun getTopHeadLines(
        @Query(value = "apiKey") apiKey: String,
        @Query(value = "sources") sources: String
    ): Single<BaseResponse<MutableList<NewsModel>>>


}