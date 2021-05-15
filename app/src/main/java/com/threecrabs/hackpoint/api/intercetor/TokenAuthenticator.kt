package com.threecrabs.hackpoint.api.intercetor

import com.threecrabs.hackpoint.cache.SharedPrefs
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(private val sharedPrefs: SharedPrefs) : Authenticator {

    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
    }

    override fun authenticate(route: Route?, response: Response): Request? {
        if (response.request.url.toString().contains("login")) {
            return null
        }
        if (response.request.header(HEADER_AUTHORIZATION) == null) {
            if (sharedPrefs.getToken() == null) {
                return null
            }
            return response.request.newBuilder()
                .header(HEADER_AUTHORIZATION, "Bearer ${sharedPrefs.getToken()?.token ?: ""}")
                .build()
        }
        return null
    }
}