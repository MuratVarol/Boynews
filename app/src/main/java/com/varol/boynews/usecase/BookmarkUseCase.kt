package com.varol.boynews.usecase

import com.varol.boynews.base.BaseUseCase
import com.varol.boynews.data.models.Bookmarks
import com.varol.boynews.database.dao.BookmarksDao
import io.reactivex.Single

class BookmarkUseCase(
    private val bookmarksDao: BookmarksDao
) : BaseUseCase() {

    fun addBookmark(url: String): Long? {
        return bookmarksDao.insert(Bookmarks(url))
    }

    fun deleteBookmark(url: String) {
        bookmarksDao.delete(Bookmarks(url))
    }

    fun getSelectedBookmark(url: String): Single<Bookmarks> {
        return bookmarksDao.getSelectedBookmark(url)
    }

    fun getAllBookmarks(): Single<List<Bookmarks>> {
        return bookmarksDao.getBookmarks()
    }

}