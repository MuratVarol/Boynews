package com.varol.boynews.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.varol.boynews.base.BaseVM
import com.varol.boynews.models.NewsModel
import com.varol.boynews.models.SourceModel
import com.varol.boynews.remote.DataHolder
import com.varol.boynews.usecase.GetNewsUseCase
import com.varol.boynews.usecase.GetSourcesUseCase
import com.varol.boynews.usecase.NewsMappingUseCase
import com.varol.boynews.util.listener.ItemClickListener
import com.varol.boynews.view_entity.NewsViewEntity

class NewsVM(
    private val getNewsUseCase: GetNewsUseCase,
    private val getSourcesUseCase: GetSourcesUseCase,
    private val newsMappingUseCase: NewsMappingUseCase

) : BaseVM() {

    val newsList = MutableLiveData<MutableList<NewsModel>>()
    val sourcesList = MutableLiveData<MutableList<SourceModel>>()
    val selectedNews = MutableLiveData<NewsViewEntity>()
    val selectedSource = MutableLiveData<SourceModel>()

    val newsViewEntityList = MutableLiveData<MutableList<NewsViewEntity>>()


    val newsClickListener: ItemClickListener<NewsViewEntity> = object
        : ItemClickListener<NewsViewEntity> {
        override fun onItemClick(view: View, item: NewsViewEntity, position: Int) {

            selectedNews.postValue(item)

        }
    }

    val sourceClickListener: ItemClickListener<SourceModel> = object
        : ItemClickListener<SourceModel> {
        override fun onItemClick(view: View, item: SourceModel, position: Int) {

            selectedSource.postValue(item)

        }
    }


    fun getAllSources() {
        val disposable = getSourcesUseCase
            .getSources()
            .observeOn(getBackgroundScheduler())
            .subscribeOn(getBackgroundScheduler())
            .subscribe({ data ->

                when (data) {
                    is DataHolder.Success -> {

                        if (data.data.status == "ok") {
                            sourcesList.postValue(data.data.sources)
                        }

                    }
                    is DataHolder.Error -> {
                        errorMessage.postValue("Failed to download sources.")
                    }
                }
            }, {
                errorMessage.postValue(it.toString())
            })
        disposables.add(disposable)
    }


    fun getAllNews(selectedSource: String) {
        val disposable = getNewsUseCase
            .getNews(selectedSource)
            .observeOn(getBackgroundScheduler())
            .subscribeOn(getBackgroundScheduler())
            .subscribe({ data ->

                when (data) {
                    is DataHolder.Success -> {

                        if (data.data.status == "ok") {
                            newsViewEntityList.postValue(
                                newsMappingUseCase.newsModelToNewsViewEntity(
                                    data.data.articles
                                )
                            )
                        }

                    }
                    is DataHolder.Error -> {
                        errorMessage.postValue("Failed to download sources.")
                    }
                }
            }, {
                errorMessage.postValue(it.toString())
            })
        disposables.add(disposable)
    }
}