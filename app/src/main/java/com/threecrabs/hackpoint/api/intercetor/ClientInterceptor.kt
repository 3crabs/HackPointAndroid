package com.threecrabs.hackpoint.api.intercetor

import com.threecrabs.hackpoint.cache.SharedPrefs
import okhttp3.Interceptor
import okhttp3.Response

class ClientInterceptor(val sharedPrefs: SharedPrefs): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder()
        builder.addHeader("Authorization", "Bearer ${sharedPrefs.getToken()?.token ?: ""}")
        val request = builder.build()
        return chain.proceed(request)
    }
}