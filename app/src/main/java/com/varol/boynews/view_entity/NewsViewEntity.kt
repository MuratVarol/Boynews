package com.varol.boynews.view_entity

/**
 * View entity for listing news
 */
data class NewsViewEntity(

    val urlAsId: String?,

    val title: String?,

    val imageUrl: String?,

    val publishTime: String?,

    val isAddedToReadList: Boolean = false

)