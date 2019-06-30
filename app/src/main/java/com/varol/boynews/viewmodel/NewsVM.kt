package com.varol.boynews.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.varol.boynews.base.BaseVM
import com.varol.boynews.models.NewsModel
import com.varol.boynews.models.SourceModel
import com.varol.boynews.remote.DataHolder
import com.varol.boynews.usecase.GetNewsUseCase
import com.varol.boynews.usecase.GetSourcesUseCase
import com.varol.boynews.util.listener.ItemClickListener

class NewsVM(
    private val getNewsUseCase: GetNewsUseCase,
    private val getSourcesUseCase: GetSourcesUseCase
) : BaseVM() {

    val newsList = MutableLiveData<MutableList<NewsModel>>()
    val sourcesList = MutableLiveData<MutableList<SourceModel>>()
    val selectedNews = MutableLiveData<NewsModel>()
    val selectedSource = MutableLiveData<SourceModel>()


    val newsClickListener: ItemClickListener<NewsModel> = object
        : ItemClickListener<NewsModel> {
        override fun onItemClick(view: View, item: NewsModel, position: Int) {

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

                        if (data.data.status == "ok")
                            sourcesList.postValue(data.data.sources)

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