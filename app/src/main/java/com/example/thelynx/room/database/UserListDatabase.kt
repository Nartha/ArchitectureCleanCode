package com.example.thelynx.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.thelynx.room.dao.AllUserDao
import com.example.thelynx.room.table.UserListEntity

@Database(entities = [UserListEntity::class], version = 1)
abstract class UserListDatabase: RoomDatabase() {
    abstract fun userListDao(): AllUserDao
}