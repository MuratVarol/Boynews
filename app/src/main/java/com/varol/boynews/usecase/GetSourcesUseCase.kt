package com.varol.boynews.usecase

import com.varol.boynews.base.BaseUseCase
import com.varol.boynews.models.SourceModel
import com.varol.boynews.remote.BaseNewsResponse
import com.varol.boynews.remote.BaseSourceResponse
import com.varol.boynews.remote.DataHolder
import com.varol.boynews.remote.repository.NewsRepository
import io.reactivex.Single

class GetSourcesUseCase(
    private val newsRepository: NewsRepository,
    private val apiKey: String
) : BaseUseCase() {

    fun getSources(): Single<DataHolder<BaseSourceResponse<MutableList<SourceModel>>>> {
        return newsRepository.getSources(
            apiKey
        )
    }
}