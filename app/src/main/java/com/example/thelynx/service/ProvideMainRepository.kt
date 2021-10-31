package com.example.thelynx.service

import android.content.Context
import com.example.thelynx.service.core.ApiInterface
import com.example.thelynx.service.repository.MainRepoImpl
import com.example.thelynx.service.repository.MainRepository

fun provideMainRepository(api: ApiInterface, context: Context): MainRepository = MainRepoImpl(api, context)