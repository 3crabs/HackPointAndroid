package com.threecrabs.hackpoint.api.dto

data class DTOPoint(
    val criterion: DTOCriterion,
    val criterionId: Int,
    val id: Int,
    val point: Int,
    val referee: DTOReferee,
    val refereeId: Int
)