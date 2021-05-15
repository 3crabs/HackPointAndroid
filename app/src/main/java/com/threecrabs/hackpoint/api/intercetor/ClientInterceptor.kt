package com.threecrabs.hackpoint.api.intercetor

import okhttp3.Interceptor
import okhttp3.Response

class ClientInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder()
        val request = builder.build()
        return chain.proceed(request)
    }
}