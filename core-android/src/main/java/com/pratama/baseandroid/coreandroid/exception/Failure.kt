package com.pratama.baseandroid.coreandroid.exception

sealed class Failure {
    object NetworkException : Failure()
    data class ServerError(val message: String?) : Failure()
    object LocalDataNotFound : Failure()
}