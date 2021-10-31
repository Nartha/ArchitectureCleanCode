package com.example.thelynx.service.core

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import android.os.Build
import retrofit2.Response
import java.lang.Exception

@SuppressLint("ObsoleteSdkInt")
fun connectNetwork(context: Context): Boolean {
    var activeNetwork = true
    val connectivityManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager?
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        connectivityManager?.run {
            this.getNetworkCapabilities(this.activeNetwork)?.run {
                activeNetwork = when {
                    hasTransport(TRANSPORT_WIFI) -> true
                    hasTransport(TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            }
        }
    } else {
        connectivityManager?.run {
            return this.activeNetworkInfo?.isConnected == true
        }
    }
    return activeNetwork
}

fun <T> success(response: Response<T>): ResultResponse.OnSuccess<T> {
    val data: T by lazy {
        response.body() ?: throw NoContentException(getStatusCodeResponse(response).code)
    }
    return ResultResponse.OnSuccess(data)
}

fun <T> error(response: Response<T>): ResultResponse.OnError =
    ResultResponse.OnError(Exception(response.message()))

fun <T> getStatusCodeResponse(response: Response<T>): StatusCodeResponse =
    StatusCodeResponse.values().find { it.code == response.code() } ?: StatusCodeResponse.Unknown