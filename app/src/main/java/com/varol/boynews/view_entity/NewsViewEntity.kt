package com.varol.boynews.view_entity

data class NewsViewEntity(

    val urlAsId: String?,

    val title: String?,

    val imageUrl: String?,

    val publishTime: String?,

    val isAddedToReadList: Boolean = false

)