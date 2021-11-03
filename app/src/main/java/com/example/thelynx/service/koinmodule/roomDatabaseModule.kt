package com.example.thelynx.service.koinmodule

import android.app.Application
import androidx.room.Room
import com.example.thelynx.room.database.UserListDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val roomDatabaseModule = module {
    single { provideDataBase(androidApplication()) }
    single { get<UserListDatabase>().userListDao() }
}

fun provideDataBase(application: Application): UserListDatabase =
    Room.databaseBuilder(application, UserListDatabase::class.java, "favorite_database")
        .build()
