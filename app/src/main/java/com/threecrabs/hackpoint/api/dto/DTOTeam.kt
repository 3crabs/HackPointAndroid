package com.threecrabs.hackpoint.api.dto

import java.io.Serializable

data class DTOTeam(
    val createdAt: Long?,
    val descriptionReferee: String?,
    val descriptionTeam: String?,
    val id: Int,
    val isBlocked: String?,
    val name: String?,
    val nameProject: String?,
    var statusFinalPitch: String?,
    val statusFirstPitch: String?,
    val statusSecondPitch: String?,
    val point: Int
): Serializable