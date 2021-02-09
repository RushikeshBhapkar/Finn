package com.example.finnmarketplace.data.remote

import com.example.finnmarketplace.utils.Result
import retrofit2.Response

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return Result.success(body)
                }
            }
            return error(" ${response.code()} ${response.message()}")

        } catch (ex: Exception) {
            return error(ex.message ?: ex.toString())
        }
    }

    private fun <T> error(message: String): Result<T> {
        return Result.error("")
    }
}
