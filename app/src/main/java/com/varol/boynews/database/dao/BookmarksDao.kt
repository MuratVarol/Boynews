package com.varol.boynews.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.varol.boynews.data.models.Bookmarks
import io.reactivex.Single

@Dao
interface BookmarksDao : BaseDao<Bookmarks> {
    @Query("SELECT * FROM bookmarks")
    fun getBookmarks(): Single<List<Bookmarks>>


    @Query("SELECT * FROM bookmarks WHERE url==:urlId")
    fun getSelectedBookmark(urlId: String): Single<Bookmarks>

}