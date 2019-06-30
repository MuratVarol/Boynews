package com.varol.boynews.di

import com.varol.boynews.viewmodel.MainVM
import com.varol.boynews.viewmodel.NewsVM
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val viewModelModule: Module = module {
    viewModel { MainVM() }
    viewModel { NewsVM(get(), get(), get()) }
}