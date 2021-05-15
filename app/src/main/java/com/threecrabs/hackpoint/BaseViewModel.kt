package com.threecrabs.hackpoint

import androidx.lifecycle.ViewModel
import com.threecrabs.hackpoint.cache.SharedPrefs
import org.koin.core.KoinComponent
import org.koin.core.inject

open class BaseViewModel: ViewModel(), KoinComponent {

    val sharedPrefs: SharedPrefs by inject()
//    protected val server: ServerRepository by inject()
}