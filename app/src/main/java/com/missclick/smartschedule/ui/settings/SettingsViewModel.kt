package com.missclick.smartschedule.ui.settings

import androidx.lifecycle.ViewModel
import com.missclick.smartschedule.App
import com.missclick.smartschedule.data.models.SettingsModel
import com.missclick.smartschedule.data.repository.ILessonRepository
import com.missclick.smartschedule.viewstates.ScheduleViewStates
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SettingsViewModel : ViewModel() {

    @Inject
    lateinit var repository: ILessonRepository

    init {
        App.appComponent.inject(this)
    }

    fun get(){
        //todo get
    }

    fun save(lessons : Int, week : Boolean, days : List<String>){
        val weekInt = if(week) 2 else 1
        val settings = SettingsModel(days = days, weeks = weekInt, couples = lessons)
        GlobalScope.launch(Dispatchers.IO) {
            repository.setSettings(settingsModel = settings)
        }
    }
}