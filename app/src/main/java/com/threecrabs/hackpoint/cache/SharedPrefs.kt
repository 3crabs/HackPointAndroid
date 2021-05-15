package com.threecrabs.hackpoint.cache

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.reflect.TypeToken
import com.threecrabs.hackpoint.api.dto.Token

class SharedPrefs(private val context: Context) : BaseSharedPrefs() {

    companion object {
        const val NAME_PREF = "AGROTRACK"
        const val TOKEN = "TOKEN_"
        const val USER = "USER_"
        const val PUSH_TOKEN = "PUSH_TOKEN_"
        const val COMPANY = "COMPANY_"
    }

    fun exit() {
        save(TOKEN, null)
        save(USER, null)
        save(COMPANY, null)
    }

    fun saveToken(token: Token?) {
        save(TOKEN, token)
    }
//
    fun getToken(): Token? {
        return get(TOKEN, object : TypeToken<Token>() {})
    }

    override fun getPrefs(): SharedPreferences {
        return context.getSharedPreferences(NAME_PREF, Context.MODE_PRIVATE)
    }



}