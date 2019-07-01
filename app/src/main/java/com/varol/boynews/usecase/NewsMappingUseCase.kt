package com.varol.boynews.usecase

import com.varol.boynews.base.BaseUseCase
import com.varol.boynews.data.models.NewsModel
import com.varol.boynews.data.view_entity.NewsViewEntity
import com.varol.boynews.extension.getDayTime


class NewsMappingUseCase(
) : BaseUseCase() {

    /**
     * Maps NewsModel to NewsViewEntity
     */
    fun newsModelToNewsViewEntity(newList: List<NewsModel>): MutableList<NewsViewEntity> {

        val newsViewEntityList = mutableListOf<NewsViewEntity>()

        newList.forEach { newsModel ->
            val newsViewEntity = NewsViewEntity(
                urlAsId = newsModel.url,
                title = newsModel.title,
                imageUrl = newsModel.urlToImage,
                publishTime = parseDate(newsModel.publishedAt)
            )

            newsViewEntityList.add(newsViewEntity)
        }
        return newsViewEntityList
    }

    private fun parseDate(date: String?): String {
        return date?.getDayTime() ?: ""
    }

    private fun isAddedToReadList(urlAsId: String?): Boolean {
        return false
    }
}