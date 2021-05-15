package com.threecrabs.hackpoint.ui.commands

import androidx.lifecycle.MutableLiveData
import com.threecrabs.hackpoint.BaseViewModel
import com.threecrabs.hackpoint.api.dto.DTOTeam
import io.reactivex.rxjava3.schedulers.Schedulers

class CommandViewModel: BaseViewModel() {

    val teams = MutableLiveData<List<DTOTeam>>()

    fun getTeams() {
        server.getTeam()
            .subscribeOn(Schedulers.io())
            .subscribe({
                teams.postValue(it)
            }, {

            })
    }

}