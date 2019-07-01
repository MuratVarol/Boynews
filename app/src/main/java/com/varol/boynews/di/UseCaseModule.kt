package com.varol.boynews.di

import com.varol.boynews.usecase.BookmarkUseCase
import com.varol.boynews.usecase.GetNewsUseCase
import com.varol.boynews.usecase.GetSourcesUseCase
import com.varol.boynews.usecase.NewsMappingUseCase
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val useCaseModule: Module = module {
    single { GetSourcesUseCase(get(), get(name = "apiKey")) }
    single { GetNewsUseCase(get(), get(name = "apiKey")) }
    single { NewsMappingUseCase() }
    single { BookmarkUseCase(get()) }
}