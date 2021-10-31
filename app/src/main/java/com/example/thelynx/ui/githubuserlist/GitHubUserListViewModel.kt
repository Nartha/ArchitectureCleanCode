package com.example.thelynx.ui.githubuserlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thelynx.service.core.ResultResponse
import com.example.thelynx.service.core.api.GithubUserList
import com.example.thelynx.service.core.api.UserList
import com.example.thelynx.service.repository.FavouriteRepository
import com.example.thelynx.service.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class GitHubUserListViewModel(
    private val mainRepo: MainRepository,
    private val favouriteRepo: FavouriteRepository
) : ViewModel() {

    val dataGitHubUserList = MutableLiveData<GithubUserList>()
    val userList = MutableLiveData<List<UserList>>()

    fun getDataUserList() {
        viewModelScope.launch {
            when (val data = mainRepo.getDataGitHubUserList()) {
                is ResultResponse.OnSuccess -> {
                    data.response.let {
                        Timber.i("test3: $it")
                        userList.value = it
                        dataGitHubUserList.value = it
                    }
                }
                is ResultResponse.OnError -> {
                    Timber.i("ResultResponse.OnError: ${data.message}")
                }
            }
        }
    }


    suspend fun updateFavourite(node_id: String, clickLike: Boolean) {
        viewModelScope.launch {
            favouriteRepo.updateClickLike(node_id, clickLike)
        }
    }

    suspend fun insertFavorite(node_id: UserList) {
        favouriteRepo.insertFavourite(node_id)
    }

    fun getAllUser() {
        CoroutineScope(Dispatchers.Default).launch {
            Timber.i("test: ${favouriteRepo.getAllUser()}")
           userList.postValue(favouriteRepo.getAllUser())
       }
    }

}