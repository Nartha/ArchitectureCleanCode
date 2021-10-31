package com.example.thelynx.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.thelynx.service.core.api.UserList

@Database(entities = [UserList::class], version = 1)
abstract class FavouriteDatabase: RoomDatabase() {

    abstract fun favoriteDao(): FavouriteDao
}