package com.varol.boynews.di

import com.varol.boynews.usecase.GetNewsUseCase
import com.varol.boynews.usecase.GetSourcesUseCase
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val useCaseModule: Module = module {
    single { GetSourcesUseCase(get(), get()) }
    single { GetNewsUseCase(get(), get()) }
}