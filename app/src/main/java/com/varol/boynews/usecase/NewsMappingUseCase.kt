package com.varol.boynews.usecase

import com.varol.boynews.base.BaseUseCase
import com.varol.boynews.models.NewsModel
import com.varol.boynews.view_entity.NewsViewEntity


class NewsMappingUseCase(
) : BaseUseCase() {

    /**
     * Maps NewsModel to NewsViewEntity
     */
    fun newsModelToNewsViewEntity(newList: List<NewsModel>): List<NewsViewEntity> {

        val newsViewEntityList = mutableListOf<NewsViewEntity>()

        newList.forEach { newsModel ->
            val newsViewEntity = NewsViewEntity(
                urlAsId = newsModel.url,
                title = newsModel.title,
                imageUrl = newsModel.urlToImage,
                publishTime = parseDate(newsModel.publishedAt),
                isAddedToReadList = isAddedToReadList(newsModel.url)
            )

            newsViewEntityList.add(newsViewEntity)
        }
        return newsViewEntityList
    }

    private fun parseDate(date: String?): String {


        return ""
    }

    private fun isAddedToReadList(urlAsId: String?): Boolean {
        return false
    }
}