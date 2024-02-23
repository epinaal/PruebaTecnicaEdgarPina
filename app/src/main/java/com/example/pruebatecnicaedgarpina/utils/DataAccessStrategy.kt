package com.example.pruebatecnicaedgarpina.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers

fun <T, A> getOperation(
    localDatabase: () -> LiveData<T>,
    remoteCall: suspend () -> Resource<A>,
    cacheResult: suspend (A) -> Unit
): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val source = localDatabase.invoke().map { Resource.success(it) }
        emitSource(source)

        val responseStatus = remoteCall.invoke()
        if (responseStatus.status == Resource.Status.SUCCESS) {
            cacheResult(responseStatus.data!!)

        } else if (responseStatus.status == Resource.Status.ERROR) {
            emit(Resource.error(responseStatus.message!!))
            emitSource(source)
        }
    }