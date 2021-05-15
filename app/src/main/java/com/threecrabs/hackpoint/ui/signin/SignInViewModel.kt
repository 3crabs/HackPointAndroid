package com.threecrabs.hackpoint.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.threecrabs.hackpoint.BaseViewModel
import com.threecrabs.hackpoint.Event
import com.threecrabs.hackpoint.R
import io.reactivex.rxjava3.schedulers.Schedulers

class SignInViewModel : BaseViewModel() {

    val inputError = MutableLiveData<Event<Map<String, Int>>>()
    val success = MutableLiveData<Event<Boolean>>()

    private fun signIn(
        login: String,
        password: String
    ) {
        server.login(login, password)
            .subscribeOn(Schedulers.io())
            .subscribe({
               sharedPrefs.saveToken(it)
                success.postValue(Event(true))
            }, {

            })
    }

    fun signInWithValidation(
        login: String,
        password: String
    ) {
        val mapError = mutableMapOf<String, Int>()
        if (login.isEmpty()) {
            mapError[LOGIN] = R.string.empty_value
        }
        if (password.isEmpty()) {
            mapError[PASSWORD] = R.string.empty_value
        }
        if (mapError.isEmpty()) {
            signIn(
                login,
                password
            )
        } else {
            inputError.postValue(Event(mapError))
        }
    }

    companion object {
        const val LOGIN = "LOGIN"
        const val PASSWORD = "PASSWORD"
    }
}