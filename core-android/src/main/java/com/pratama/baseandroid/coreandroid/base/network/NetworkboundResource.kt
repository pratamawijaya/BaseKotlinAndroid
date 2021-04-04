package com.pratama.baseandroid.coreandroid.base.network

import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: suspend () -> Flow<ResultType?>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline onFetchFailed: (Throwable) -> Unit = { Unit },
    crossinline shouldFetch: (ResultType?) -> Boolean = { true }
) = flow<Result<ResultType>> {

    emit(Result.loading(null))
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Result.loading(data))
        try {
            saveFetchResult(fetch())
            query().map { Result.success(it) }
        } catch (throwable: Throwable) {
            onFetchFailed(throwable)
            query().map {
                Result.error(
                    msg = throwable.localizedMessage,
                    data = it,
                    throwable = throwable
                )
            }
        }
    } else {
        query().map { Result.success(it) }
    }

    emitAll(flow)
}
