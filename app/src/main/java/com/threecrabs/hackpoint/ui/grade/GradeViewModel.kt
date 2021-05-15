package com.threecrabs.hackpoint.ui.grade

import androidx.lifecycle.MutableLiveData
import com.threecrabs.hackpoint.BaseViewModel
import com.threecrabs.hackpoint.api.dto.DTOPoint
import com.threecrabs.hackpoint.api.dto.DTOTeam
import io.reactivex.rxjava3.schedulers.Schedulers

class GradeViewModel: BaseViewModel() {

    lateinit var team: DTOTeam
    val points = MutableLiveData<List<DTOPoint>>()

    fun getPoints() {
        server.getPoints(team.id)
            .subscribeOn(Schedulers.io())
            .subscribe({
                points.postValue(it.points)
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

}