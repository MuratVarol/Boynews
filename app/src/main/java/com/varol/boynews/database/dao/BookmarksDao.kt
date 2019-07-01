package com.varol.boynews.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.varol.boynews.data.models.Bookmarks
import io.reactivex.Maybe

@Dao
interface BookmarksDao : BaseDao<Bookmarks> {
    @Query("SELECT * FROM bookmarks")
    fun getBookmarks(): Maybe<List<Bookmarks>>
}