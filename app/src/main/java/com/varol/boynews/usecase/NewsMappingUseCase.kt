package com.varol.boynews.usecase

import com.varol.boynews.base.BaseUseCase
import com.varol.boynews.data.models.Bookmarks
import com.varol.boynews.data.models.NewsModel
import com.varol.boynews.data.view_entity.NewsViewEntity
import com.varol.boynews.extension.getDayTime


class NewsMappingUseCase(
) : BaseUseCase() {

    /**
     * Maps NewsModel to NewsViewEntity
     */
    fun newsModelToNewsViewEntity(
        newList: List<NewsModel>,
        bookmarkList: List<Bookmarks>?
    ): MutableList<NewsViewEntity> {

        val newsViewEntityList = mutableListOf<NewsViewEntity>()

        newList.forEach { newsModel ->
            val newsViewEntity = NewsViewEntity(
                urlAsId = newsModel.url,
                title = newsModel.title,
                imageUrl = newsModel.urlToImage,
                publishTime = parseDate(newsModel.publishedAt),
                isAddedToReadList = isAddedToReadList(newsModel.url, bookmarkList)
            )

            newsViewEntityList.add(newsViewEntity)
        }
        return newsViewEntityList
    }

    private fun parseDate(date: String?): String {
        return date?.getDayTime() ?: ""
    }

    private fun isAddedToReadList(urlAsId: String?, bookmarkList: List<Bookmarks>?): Boolean {
        urlAsId?.let {
            val bookmark = bookmarkList?.firstOrNull {
                it.url == urlAsId
            }
            return bookmark != null
        } ?: return false
    }
}