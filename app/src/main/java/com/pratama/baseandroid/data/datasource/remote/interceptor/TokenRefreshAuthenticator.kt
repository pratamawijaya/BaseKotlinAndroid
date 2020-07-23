package com.pratama.baseandroid.data.datasource.remote.interceptor

import com.github.ajalt.timberkt.e
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import java.io.IOException

class TokenRefreshAuthenticator : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? = when {
        response.retryCount > 2 -> null
        else -> response.createSignedRequest()
    }

    private fun Response.createSignedRequest(): Request? = try {
        // todo: setup auth repo
//        val accessToken = authenticationRepository.fetchFreshAccessToken()
        request.signWithToken("my_token")
    } catch (error: IOException) {
        e { "Failed to resign request" }
        null
    }

    private fun Request.signWithToken(accessToken: String) =
        newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()
}

private val Response.retryCount: Int
    get() {
        var currentResponse = priorResponse
        var result = 0
        while (currentResponse != null) {
            result++
            currentResponse = currentResponse.priorResponse
        }
        return result
    }
