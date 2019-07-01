package com.varol.boynews.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarks")
data class Bookmarks(
    @PrimaryKey val url: String
)