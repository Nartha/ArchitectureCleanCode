package com.example.thelynx.service.core

import com.example.thelynx.service.core.api.GithubUserList
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("users")
    suspend fun getDataUserList(): Response<GithubUserList>
}