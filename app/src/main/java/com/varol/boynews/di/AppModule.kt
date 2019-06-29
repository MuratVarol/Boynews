package com.varol.boynews.di

import org.koin.dsl.module.module
import java.util.*

/**
 * appModule definitions for dependency injection
 *
 * This part will consist of injection Application based objects
 */
val appModule = module {
    factory("lang_code") { getLocaleLanguage() }
}

/**
 * returns country code Example: "us" for United States
 */
fun getLocaleLanguage() {
    Locale.getDefault().country
}