package com.example.thelynx.service.koinmodule

import com.example.thelynx.service.provideMainRepository
import com.example.thelynx.service.repository.UserListRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repoModule = module {
    factory { provideMainRepository(get(), androidContext()) }
    single { UserListRepository(get()) }
}