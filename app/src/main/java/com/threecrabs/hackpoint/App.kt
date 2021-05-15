package com.threecrabs.hackpoint

import android.app.Application
import com.threecrabs.hackpoint.cache.SharedPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App: Application() {

    private val appModule = module {
//        single { ServerRepository(RetrofitServerApi.create(get())) }
        single { SharedPrefs(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}