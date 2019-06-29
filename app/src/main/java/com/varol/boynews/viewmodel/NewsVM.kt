package com.varol.boynews.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.varol.boynews.base.BaseVM
import com.varol.boynews.models.NewsModel
import com.varol.boynews.models.SourceModel
import com.varol.boynews.util.listener.ItemClickListener

class NewsVM : BaseVM() {
    val dummy = MutableLiveData<String>()

    val newsList = MutableLiveData<NewsModel>()
    val sourcesList = MutableLiveData<SourceModel>()


    val newsClickListener: ItemClickListener<NewsModel> = object
        : ItemClickListener<NewsModel> {
        override fun onItemClick(view: View, item: NewsModel, position: Int) {


        }
    }

    val sourceClickListener: ItemClickListener<SourceModel> = object
        : ItemClickListener<SourceModel> {
        override fun onItemClick(view: View, item: SourceModel, position: Int) {


        }
}
}