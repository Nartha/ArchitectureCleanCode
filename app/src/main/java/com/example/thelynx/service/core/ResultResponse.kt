package com.example.thelynx.service.core

sealed class ResultResponse<out T> {
    data class OnSuccess<out T>(val response: T) : ResultResponse<T>()
    class OnError(
        private val exception: Exception,
        val message: String? = exception.localizedMessage
    ) : ResultResponse<Nothing>()
}

class NoContentException constructor(
    val code: Int,
    override val message: String? = "The server has successfully fulfilled the request with the code ($code)" +
            " and that there is no additional content to send in the response payload body."
) :
    Throwable(message)