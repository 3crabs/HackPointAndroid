package com.threecrabs.hackpoint.api

import com.google.gson.GsonBuilder
import com.threecrabs.hackpoint.Const
import com.threecrabs.hackpoint.api.dto.*
import com.threecrabs.hackpoint.api.intercetor.ClientInterceptor
import com.threecrabs.hackpoint.api.intercetor.TokenAuthenticator
import com.threecrabs.hackpoint.cache.SharedPrefs
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Maybe
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface RetrofitServerApi {

    @POST("login")
    fun login(@Body login: DTORequestLogin): Maybe<Token>

    @GET("admin/team")
    fun getTeams(): Maybe<List<DTOTeam>>

    @GET("referee/point/note")
    fun getPoints(@Query("teamId") teamId: Int): Maybe<DTOPoints>

    @POST("admin/referee/demofest")
    fun demofest(): Maybe<Any>

    @PUT("referee/point/{id}")
    fun degreePoint(@Path("id") id: Int, @Body pointRequest: DTOPointRequest): Maybe<Any>

    companion object {
        fun create(sharedPrefs: SharedPrefs): RetrofitServerApi {
            val loggingInterceptor = HttpLoggingInterceptor()

            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .connectTimeout(Const.TIMEOUT_CONNECT, TimeUnit.SECONDS)
                .readTimeout(Const.TIMEOUT_RW, TimeUnit.MINUTES)
                .authenticator(TokenAuthenticator(sharedPrefs))
                .addInterceptor(ClientInterceptor(sharedPrefs))
                .addInterceptor(loggingInterceptor)
                .build()

            val gson = GsonBuilder()
                .setLenient()
                .serializeNulls()
                .create()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(Const.WORK_BASE_URL)
                .client(client)
                .build()
            return retrofit.create(RetrofitServerApi::class.java)
        }
    }
}