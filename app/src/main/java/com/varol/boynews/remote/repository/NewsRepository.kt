package com.varol.boynews.remote.repository

import com.varol.boynews.base.BaseRepository
import com.varol.boynews.base.service
import com.varol.boynews.data.models.NewsModel
import com.varol.boynews.data.models.SourceModel
import com.varol.boynews.remote.Api
import com.varol.boynews.remote.BaseNewsResponse
import com.varol.boynews.remote.BaseSourceResponse
import com.varol.boynews.remote.DataHolder
import io.reactivex.Single

class NewsRepository(private val api: Api) : BaseRepository() {

    fun getSources(apiKey: String): Single<DataHolder<BaseSourceResponse<MutableList<SourceModel>>>> {
        return service.sendRequest(
            api.getSources(apiKey)
        )
    }

    fun getNews(apiKey: String, source: String): Single<DataHolder<BaseNewsResponse<MutableList<NewsModel>>>> {
        return service.sendRequest(
            api.getTopHeadLines(apiKey, source)
        )
    }

    fun getSearchedNews(
        apiKey: String,
        searchedText: String
    ): Single<DataHolder<BaseNewsResponse<MutableList<NewsModel>>>> {
        return service.sendRequest(
            api.getSearchedNews(apiKey, searchedText)
        )
    }

}