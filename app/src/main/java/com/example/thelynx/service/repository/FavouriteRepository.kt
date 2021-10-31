package com.example.thelynx.service.repository

import com.example.thelynx.room.FavouriteDao
import com.example.thelynx.service.core.api.UserList

class FavouriteRepository(private val favourite: FavouriteDao) {
    suspend fun insertFavourite(node_id: UserList) = favourite.insert(node_id)

    fun getAllUser() = favourite.getAllUser()

    fun getAlphabetizedByASC() = favourite.getAlphabetizedByASC()

    fun getAlphabetizedByDESC() = favourite.getAlphabetizedByDesc()

    suspend fun updateClickLike(node_id: String, clickLike: Boolean) = favourite.updateClickLike(node_id, clickLike)


}