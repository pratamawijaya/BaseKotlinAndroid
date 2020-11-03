package com.pratama.baseandroid.coreandroid.base.network

import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: suspend () -> Flow<ResultType?>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline onFetchFailed: (Throwable) -> Unit = { Unit },
    crossinline shouldFetch: (ResultType?) -> Boolean = { true }
) = flow<Resource<ResultType>> {

    emit(Resource.loading(null))
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Resource.loading(data))
        try {
            saveFetchResult(fetch())
            query().map { Resource.success(it) }
        } catch (throwable: Throwable) {
            onFetchFailed(throwable)
            query().map {
                Resource.error(
                    msg = throwable.localizedMessage,
                    data = it,
                    throwable = throwable
                )
            }
        }
    } else {
        query().map { Resource.success(it) }
    }

    emitAll(flow)
}
