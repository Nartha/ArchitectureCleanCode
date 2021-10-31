package com.example.thelynx.service.koinmodule

import com.example.thelynx.utils.SharedPreference
import com.example.thelynx.service.createApi
import com.example.thelynx.service.createClient
import com.example.thelynx.service.retrofitBuild
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreModule = module {
    single { createApi(get()) }
    single { createClient() }
    single { retrofitBuild(get()) }

    single { SharedPreference(androidContext()) }
}