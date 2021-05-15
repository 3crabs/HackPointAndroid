package com.threecrabs.hackpoint.ui.grade

import androidx.lifecycle.MutableLiveData
import com.threecrabs.hackpoint.BaseViewModel
import com.threecrabs.hackpoint.Event
import com.threecrabs.hackpoint.api.dto.DTONote
import com.threecrabs.hackpoint.api.dto.DTOPoint
import com.threecrabs.hackpoint.api.dto.DTOTeam
import io.reactivex.rxjava3.schedulers.Schedulers

class GradeViewModel: BaseViewModel() {

    lateinit var team: DTOTeam
    val points = MutableLiveData<List<DTOPoint>>()
    val note = MutableLiveData<DTONote>()
    val success = MutableLiveData<Event<Boolean>>()

    fun getPoints() {
        server.getPoints(team.id)
            .subscribeOn(Schedulers.io())
            .subscribe({
                points.postValue(it.points)
                it.note?.let {
                    note.postValue(it)
                }
            }, {
                val a = 1
            })
    }

    fun degrePoint(id: Int, degrePoint: Int) {
        server.degreePoint(id, degrePoint)
            .subscribeOn(Schedulers.io())
            .subscribe({

            }, {

            })
    }

    fun updateNote(text: String) {
        note.value?.id?.let { id ->
            team.statusFinalPitch = "present"
            server.updateNote(id, text, team.id)
                .subscribeOn(Schedulers.io())
                .flatMap { server.updateTeam(team) }
                .subscribe({
                    success.postValue(Event(true))
                }, {

                })
        }
    }
}