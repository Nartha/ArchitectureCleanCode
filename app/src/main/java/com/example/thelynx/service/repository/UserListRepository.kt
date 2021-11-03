package com.example.thelynx.service.repository

import com.example.thelynx.room.dao.AllUserDao
import com.example.thelynx.room.table.UserListEntity

class UserListRepository(private val allUser: AllUserDao) {
    fun insertUserListEntity(userListEntity: UserListEntity) = allUser.insert(userListEntity)

    fun getAllUser() = allUser.getAllUser()

    fun getAlphabetizedByASC() = allUser.getAlphabetizedByASC()

    fun getAlphabetizedByDESC() = allUser.getAlphabetizedByDesc()

    suspend fun updateClickLike(node_id: String, clickLike: Boolean) = allUser.updateClickLike(node_id, clickLike)

    suspend fun filterLike() = allUser.filterLike()
}