package com.varol.boynews.di

import com.varol.boynews.remote.repository.NewsRepository
import org.koin.dsl.module.Module
import org.koin.dsl.module.module


val repositoryModule: Module = module {
    single { NewsRepository(get()) }
}
