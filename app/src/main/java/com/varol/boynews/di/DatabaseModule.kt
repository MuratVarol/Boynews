package com.varol.boynews.di

import android.content.Context
import androidx.room.Room
import com.varol.boynews.APP_DATABASE_NAME
import com.varol.boynews.database.AppDatabase
import com.varol.boynews.database.dao.BookmarksDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

/**
 * appModule definitions for dependency injection
 *
 * This part will consist of injection Application based objects
 */
val databaseModule = module {
    single { createAppDatabase(androidContext()) }
    single { createBookmarksDao(get()) }
}

fun createAppDatabase(context: Context): AppDatabase {
    return Room
        .databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            APP_DATABASE_NAME
        )
        .fallbackToDestructiveMigration()
        .build()
}

fun createBookmarksDao(appDatabase: AppDatabase): BookmarksDao {
    return appDatabase.bookmarkDao()
}