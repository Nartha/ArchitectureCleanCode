package com.example.thelynx.service.repository

import com.example.thelynx.room.dao.AllUserDao
import com.example.thelynx.room.table.UserListEntity

class UserListRepository(private val allUser: AllUserDao) {
    suspend fun insertUserListEntity(userListEntity: UserListEntity) = allUser.insert(userListEntity)

    suspend fun getAllUser() = allUser.getAllUser()

    suspend fun getAlphabetizedByASC() = allUser.getAlphabetizedByASC()

    suspend fun getAlphabetizedByDESC() = allUser.getAlphabetizedByDesc()

    suspend fun updateClickLike(node_id: String, clickLike: Boolean) = allUser.updateClickLike(node_id, clickLike)

    suspend fun filterLike() = allUser.filterLike()

    suspend fun favouriteGetAlphabetizedASC() = allUser.favouriteGetAlphabetizedASC()

    suspend fun favouriteGetAlphabetizedDESC() = allUser.favouriteGetAlphabetizedDESC()
}