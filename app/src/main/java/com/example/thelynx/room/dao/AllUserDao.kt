package com.example.thelynx.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.thelynx.room.table.UserListEntity

@Dao
interface AllUserDao {
    @Query("select * from user_list_table")
    suspend fun getAllUser(): List<UserListEntity>

    @Query("select * from user_list_table order by login asc")
    suspend fun getAlphabetizedByASC(): List<UserListEntity>

    @Query("select * from user_list_table order by login desc")
    suspend fun getAlphabetizedByDesc(): List<UserListEntity>

    @Insert
    suspend fun insert(userListEntity: UserListEntity)

    @Query("update user_list_table set clickLike = :clickLike where node_id = :node_id")
    suspend fun updateClickLike(node_id: String, clickLike: Boolean)

    @Query("select * from user_list_table where clickLike = 1")
    suspend fun filterLike(): List<UserListEntity>

    @Query("select * from user_list_table where clickLike = 1 order by login asc")
    suspend fun favouriteGetAlphabetizedASC(): List<UserListEntity>

    @Query("select * from user_list_table where clickLike = 1 order by login desc")
    suspend fun favouriteGetAlphabetizedDESC(): List<UserListEntity>
}