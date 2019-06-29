package com.varol.boynews.remote.repository

import com.varol.boynews.base.BaseRepository
import com.varol.boynews.base.service
import com.varol.boynews.models.NewsModel
import com.varol.boynews.models.SourceModel
import com.varol.boynews.remote.Api
import com.varol.boynews.remote.BaseResponse
import com.varol.boynews.remote.DataHolder
import io.reactivex.Single

class NewsRepository(private val api: Api) : BaseRepository() {

    fun getSources(apiKey: String): Single<DataHolder<BaseResponse<MutableList<SourceModel>>>> {
        return service.sendRequest(
            api.getSources(apiKey)
        )
    }

    fun getNews(apiKey: String, source: String): Single<DataHolder<BaseResponse<MutableList<NewsModel>>>> {
        return service.sendRequest(
            api.getTopHeadLines(apiKey, source)
        )
    }

}