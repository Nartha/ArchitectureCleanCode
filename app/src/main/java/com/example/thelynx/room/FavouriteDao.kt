package com.example.thelynx.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.thelynx.service.core.api.UserList

@Dao
interface FavouriteDao {
    @Query("select * from favourite_table")
    fun getAllUser(): List<UserList>

    @Query("select * from favourite_table order by login asc")
    fun getAlphabetizedByASC(): List<UserList>

    @Query("select * from favourite_table order by login desc")
    fun getAlphabetizedByDesc(): List<UserList>

    @Insert
    suspend fun insert(userList: UserList)

    @Query("update favourite_table set clickLike = :clickLike where node_id = :node_id")
    suspend fun updateClickLike(node_id: String, clickLike: Boolean)
}