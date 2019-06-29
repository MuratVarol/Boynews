package com.varol.boynews.usecase

import com.varol.boynews.base.BaseUseCase
import com.varol.boynews.models.NewsModel
import com.varol.boynews.remote.BaseResponse
import com.varol.boynews.remote.DataHolder
import com.varol.boynews.remote.repository.NewsRepository
import io.reactivex.Single

class GetNewsUseCase(
    private val newsRepository: NewsRepository,
    private val apiKey: String
) : BaseUseCase() {

    fun getNews(source: String): Single<DataHolder<BaseResponse<MutableList<NewsModel>>>> {
        return newsRepository.getNews(
            apiKey,
            source
        )
    }
}