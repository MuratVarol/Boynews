package com.varol.boynews.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.varol.boynews.base.BaseVM
import com.varol.boynews.data.models.NewsModel
import com.varol.boynews.data.models.SourceModel
import com.varol.boynews.data.view_entity.NewsViewEntity
import com.varol.boynews.remote.DataHolder
import com.varol.boynews.usecase.BookmarkUseCase
import com.varol.boynews.usecase.GetNewsUseCase
import com.varol.boynews.usecase.GetSourcesUseCase
import com.varol.boynews.usecase.NewsMappingUseCase
import com.varol.boynews.util.binding_adapters.SingleLiveEvent
import com.varol.boynews.util.listener.ItemClickListener
import java.util.concurrent.TimeUnit

private const val REFRESH_TIMER = 60_000L

class NewsVM(
    private val getNewsUseCase: GetNewsUseCase,
    private val getSourcesUseCase: GetSourcesUseCase,
    private val newsMappingUseCase: NewsMappingUseCase,
    private val bookmarkUseCase: BookmarkUseCase

) : BaseVM() {

    val isInProgress = SingleLiveEvent<Boolean>()

    val sourcesList = MutableLiveData<MutableList<SourceModel>>()
    val selectedNews = SingleLiveEvent<NewsViewEntity>()
    val selectedSource = SingleLiveEvent<SourceModel>()

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

    val addToBookmarkClickListener: ItemClickListener<NewsViewEntity> = object
        : ItemClickListener<NewsViewEntity> {
        override fun onItemClick(view: View, item: NewsViewEntity, position: Int) {

            item.urlAsId?.let {
                if (item.isAddedToReadList) {
                    bookmarkUseCase.addBookmark(it)
                } else {
                    bookmarkUseCase.deleteBookmark(it)
                }
            }
        }
    }


    fun getAllSources() {

        isInProgress.postValue(true)

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

                isInProgress.postValue(false)

            }, {
                isInProgress.postValue(false)
                errorMessage.postValue(it.toString())
            })
        disposables.add(disposable)
    }

    /**
     * Download News periodically
     */
    fun getNewsPeriodically(selectedSource: String) {

        //First shot
        getAllNews(selectedSource)

        //Calls getAllNews() method every REFRESH_TIMER time
        val disposable = startCountdown(REFRESH_TIMER, TimeUnit.MILLISECONDS)
            .subscribe {
                getAllNews(selectedSource)
            }
        disposables.add(disposable)
    }

    fun getBookmarks(newsList: List<NewsModel>) {
        val disposable = bookmarkUseCase
            .getAllBookmarks()
            .subscribe { bookmarks ->
                newsViewEntityList.postValue(
                    newsMappingUseCase.newsModelToNewsViewEntity(
                        newsList, bookmarks
                    )
                )
            }
        disposables.add(disposable)
    }

    fun getAllNews(selectedSource: String) {

        isInProgress.postValue(true)

        val disposable = getNewsUseCase
            .getNews(selectedSource)
            .observeOn(getBackgroundScheduler())
            .subscribeOn(getBackgroundScheduler())
            .subscribe({ data ->

                when (data) {
                    is DataHolder.Success -> {

                        if (data.data.status == "ok") {
                            getBookmarks(data.data.articles)
                        }

                    }
                    is DataHolder.Error -> {
                        errorMessage.postValue("Failed to download sources.")
                    }
                }
                isInProgress.postValue(false)
            }, {
                isInProgress.postValue(false)
                errorMessage.postValue(it.toString())
            })
        disposables.add(disposable)
    }


}
