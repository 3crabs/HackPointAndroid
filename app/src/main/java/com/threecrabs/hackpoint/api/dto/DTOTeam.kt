package com.threecrabs.hackpoint.api.dto

data class DTOTeam(
    val createdAt: Int?,
    val descriptionReferee: String?,
    val descriptionTeam: String?,
    val id: Int?,
    val isBlocked: String?,
    val name: String?,
    val nameProject: String?,
    val statusFinalPitch: String?,
    val statusFirstPitch: String?,
    val statusSecondPitch: String?
)