package com.pratama.baseandroid.coreandroid.base.network

data class Resource<out T>(
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
        fun <T> success(data: T?): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(msg: String, data: T? = null, throwable: Throwable?): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                msg,
                throwable
            )
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(
                Status.LOADING,
                data,
                null
            )
        }
    }
}
