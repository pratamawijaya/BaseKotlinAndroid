package com.pratama.baseandroid.coreandroid.base.network

data class Result<out T>(
    val status: Status,
    val data: T?,
    val message: String?,
    val throwable: Throwable? = null
) {
    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T?): Result<T> {
            return Result(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(msg: String, data: T? = null, throwable: Throwable?): Result<T> {
            return Result(
                Status.ERROR,
                data,
                msg,
                throwable
            )
        }

        fun <T> loading(data: T? = null): Result<T> {
            return Result(
                Status.LOADING,
                data,
                null
            )
        }
    }
}
