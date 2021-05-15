package com.threecrabs.hackpoint.api.dto

data class DTORequestLogin(
    val login: String,
    val password: String,
    val isMobile: Boolean = true
)
