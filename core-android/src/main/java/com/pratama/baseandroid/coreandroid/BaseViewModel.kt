package com.pratama.baseandroid.coreandroid

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout

abstract class BaseViewModel<T> : ViewModel() {

    private val defaultNumberOfRetries = 3
    private val defaultTimeout = 10000L // 10sec


    fun uiState(): LiveData<T> = uiState
    protected val uiState: MutableLiveData<T> = MutableLiveData()

    protected suspend fun <T> retryWithTimeout(
        numberOfRetries: Int = defaultNumberOfRetries,
        timeout: Long = defaultTimeout,
        block: suspend () -> T
    ) = retry(numberOfRetries) {
        withTimeout(timeout) {
            block()
        }
    }

    protected suspend fun <T> retry(
        numberOfRetries: Int,
        delayBetweenRetries: Long = 100,
        block: suspend () -> T
    ): T {
        repeat(numberOfRetries) {
            try {
                return block()
            } catch (exception: Exception) {
                Log.e("error", exception.localizedMessage)
            }
            delay(delayBetweenRetries)
        }
        return block() // last attempt
    }


}
