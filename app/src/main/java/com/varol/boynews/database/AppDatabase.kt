package com.varol.boynews.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.varol.boynews.BuildConfig
import com.varol.boynews.data.models.Bookmarks
import com.varol.boynews.database.dao.BookmarksDao

@Database(
    entities = [Bookmarks::class], version = BuildConfig.VERSION_CODE, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookmarkDao(): BookmarksDao

}