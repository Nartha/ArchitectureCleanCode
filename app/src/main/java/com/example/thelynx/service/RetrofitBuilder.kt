package com.example.thelynx.service

import com.example.thelynx.BaseUrl
import com.example.thelynx.BuildConfig
import com.example.thelynx.service.core.ApiInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun createClient(): OkHttpClient {
    return OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .apply {
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor().apply {
                    this.level = HttpLoggingInterceptor.Level.BODY
                }
            }
        }.build()
}

fun retrofitBuild(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BaseUrl.base_url)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(client)
        .build()
}

fun createApi(retrofit: Retrofit): ApiInterface = retrofit.create(ApiInterface::class.java)