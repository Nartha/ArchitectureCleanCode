package com.example.thelynx.service.repository

import android.content.Context
import com.example.thelynx.service.core.*
import com.example.thelynx.service.core.api.GithubUserList

interface MainRepository {
    suspend fun getDataGitHubUserList(): ResultResponse<GithubUserList>
}

class MainRepoImpl(
    private val api: ApiInterface,
    private val context: Context
) : MainRepository {
    override suspend fun getDataGitHubUserList(): ResultResponse<GithubUserList> {
        return try {
            if (connectNetwork(context)) {
                val response = api.getDataUserList()
                if (response.isSuccessful) {
                    success(response)
                }
                else {
                    error(response)
                }
            }
            else {
                error("can't network connectivity")
            }
        }
        catch (e: Exception) {
            error(e)
        }
    }
}