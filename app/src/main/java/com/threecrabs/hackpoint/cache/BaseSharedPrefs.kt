package com.threecrabs.hackpoint.cache

import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

abstract class BaseSharedPrefs {

    private val GSON = GsonBuilder().setDateFormat("dd.MM.yyyy").create()

    fun <T> save(key: String, data: T) {
        getPrefs().edit().putString(key, GSON.toJson(data)).apply()
    }

    fun <T> get(key: String, typeToken: TypeToken<T>): T? {
        return GSON.fromJson<T>(getPrefs().getString(key, ""), typeToken.type)
    }

    fun delete(key: String) {
        getPrefs().edit().remove(key).apply()
    }

    fun deleteAll() {
        getPrefs().edit().clear().apply()
    }

    abstract fun getPrefs(): SharedPreferences
}