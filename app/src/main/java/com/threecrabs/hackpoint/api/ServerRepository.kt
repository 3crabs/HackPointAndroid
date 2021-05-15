package com.threecrabs.hackpoint.api

import com.threecrabs.hackpoint.api.dto.DTOPointRequest
import com.threecrabs.hackpoint.api.dto.DTORequestLogin
import com.threecrabs.hackpoint.api.dto.DTORequestNote
import com.threecrabs.hackpoint.api.dto.DTOTeam


class ServerRepository(private val api: RetrofitServerApi) {

    fun login(login: String, password: String) = api.login(DTORequestLogin(login, password))

    fun getTeam() = api.getTeams()

    fun demofest() = api.demofest()

    fun degreePoint(id: Int, degrePoint: Int) = api.degreePoint(id, DTOPointRequest(degrePoint))

    fun updateNote(id: Int, text: String, teamId: Int) = api.updateNote(id, DTORequestNote(text, teamId))

    fun updateTeam(team: DTOTeam) = api.updateTeam(team.id, team)

    fun getPoints(teamId: Int) = api.getPoints(teamId)
//
//    fun refreshToken(grantType: String, refreshToken: String) = api.refreshToken(grantType, refreshToken)

}

