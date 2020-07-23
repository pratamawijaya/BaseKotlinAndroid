package com.pratama.baseandroid.coreandroid.exception

sealed class Failure {
    object NetworkException : Failure()
    object ServerError : Failure()
    object LocalDataNotFound : Failure()
}