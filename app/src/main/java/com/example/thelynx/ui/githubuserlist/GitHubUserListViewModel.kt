package com.example.thelynx.ui.githubuserlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thelynx.room.table.UserListEntity
import com.example.thelynx.service.core.ResultResponse
import com.example.thelynx.service.core.api.GithubUserList
import com.example.thelynx.service.repository.MainRepository
import com.example.thelynx.service.repository.UserListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class GitHubUserListViewModel(
    private val mainRepo: MainRepository,
    private val userListRepo: UserListRepository
) : ViewModel() {

    val userListEntity = MutableLiveData<List<UserListEntity>>()
    val userListFavourite = MutableLiveData<List<UserListEntity>>()
    val filterASC = MutableLiveData<List<UserListEntity>>()
    val filterDESC = MutableLiveData<List<UserListEntity>>()

    private fun getDataUserList() {
        viewModelScope.launch {
            when (val data = mainRepo.getDataGitHubUserList()) {
                is ResultResponse.OnSuccess -> {
                    insertUserListEntity(data.response)
                }
                is ResultResponse.OnError -> {
                    Timber.i("ResultResponse.OnError: ${data.message}")
                }
            }
        }
    }

    fun filterLike() {
        viewModelScope.launch {
            userListFavourite.postValue(userListRepo.filterLike())
        }
    }

    fun updateClickLikeUserListEntity(node_id: String, clickLike: Boolean) {
        viewModelScope.launch {
            userListRepo.updateClickLike(node_id, clickLike)
        }
    }

    private fun insertUserListEntity(githubUserList: GithubUserList) {
        viewModelScope.launch {
            with(userListRepo.getAllUser()) {
                if (this.isEmpty()) {
                    githubUserList.let {
                        it.forEach { userListEntity ->
                            userListRepo.insertUserListEntity(userListEntity)
                        }
                    }
                } else {
                    githubUserList.let {
                        for (pos in it.indices) {
                            if (it[pos].node_id != this[pos].node_id) {
                                userListRepo.insertUserListEntity(it[pos])
                            }
                        }
                    }
                }
                getAllUser()
            }
        }
    }

    fun getAllUser() {
        viewModelScope.launch {
            with(userListRepo.getAllUser()) {
                if (this.isNotEmpty()) {
                    userListEntity.postValue(this)
                } else {
                    getDataUserList()
                }
            }
        }
    }

    fun getAlphabetizedByASC(filterPage: String) {
        viewModelScope.launch {
            if (filterPage == "FavouriteFragment") {
                filterDESC.postValue(userListRepo.favouriteGetAlphabetizedASC())
            }
            else {
                filterASC.postValue(userListRepo.getAlphabetizedByASC())
            }
        }
    }

    fun getAlphabetizedByDESC(filterPage: String) {
        viewModelScope.launch {
            if (filterPage == "FavouriteFragment") {
                filterDESC.postValue(userListRepo.favouriteGetAlphabetizedDESC())
            }
            else {
                filterDESC.postValue(userListRepo.getAlphabetizedByDESC())
            }
        }
    }
}