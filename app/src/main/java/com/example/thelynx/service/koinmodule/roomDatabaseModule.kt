package com.example.thelynx.service.koinmodule

import android.app.Application
import androidx.room.Room
import com.example.thelynx.room.FavouriteDao
import com.example.thelynx.room.FavouriteDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val roomDatabaseModule = module {
    single { provideDataBase(androidApplication()) }
    single { get<FavouriteDatabase>().favoriteDao() }
}

fun provideDataBase(application: Application): FavouriteDatabase =
    Room.databaseBuilder(application, FavouriteDatabase::class.java, "favorite_database")
        .build()
