package com.threecrabs.hackpoint

import android.app.Activity

fun Activity.changeAuth() {
    if (this is MainActivity) {
        this.changeAuth()
    }
}