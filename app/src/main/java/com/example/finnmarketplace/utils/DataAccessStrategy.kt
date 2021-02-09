package com.example.finnmarketplace.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.map
import com.example.finnmarketplace.utils.Result.Status.*

fun <T, A> executeGetOperation(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Result<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<Result<T>> =
    liveData(Dispatchers.IO) {
        emit(Result.loading())
        val source = databaseQuery.invoke().map { Result.success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == SUCCESS) {
            saveCallResult(responseStatus.data!!)
        } else if (responseStatus.status == ERROR) {
            emit(Result.error(responseStatus.message!!))
            emitSource(source)
        }
    }
