package com.threecrabs.hackpoint.api

import com.threecrabs.hackpoint.api.dto.DTORequestLogin


class ServerRepository(private val api: RetrofitServerApi) {

    fun login(login: String, password: String) = api.login(DTORequestLogin(login, password))

    fun getTeam() = api.getTeams()

    fun demofest() = api.demofest()

    fun getPoints(teamId: Int) = api.getPoints(teamId)
//
//    fun refreshToken(grantType: String, refreshToken: String) = api.refreshToken(grantType, refreshToken)

}

