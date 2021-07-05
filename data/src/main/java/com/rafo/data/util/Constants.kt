package com.rafo.data.util

import com.rafo.entities.CallException
import com.rafo.entities.Result
import retrofit2.Response

suspend fun <R> makeApiCall(
    call: suspend () -> Result<R>,
    errorMessage: Int = 4567
) = try {
    call()
} catch (e: Exception) {
    Result.Error(CallException<Nothing>(errorMessage, e.localizedMessage))
}

fun <R> analyzeResponse(response: Response<R>): Result<R> {
    return when (response.code()) {
        200 -> {
            val responseBody = response.body()
            Result.Success(responseBody)
        }
        else -> {
            val responseMessage = response.errorBody()?.string()
            Result.Error(CallException<Nothing>(response.code(), responseMessage))
        }
    }
}