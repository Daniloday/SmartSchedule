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

    var daysString = listOf<String>("Monday","Tuesday","Wednesday","Thursday","Friday")

    @Inject
    lateinit var repository: ILessonRepository
    var settings : SettingsModel? = null
    init {
        App.appComponent.inject(this)
    }

    suspend fun getSettings(){
        settings = repository.getSettings()
        daysString = settings!!.days
    }

    fun save(lessons : Int, week : Boolean){
        val weekInt = if(week) 2 else 1
        val settings = SettingsModel(days = daysString, weeks = weekInt, couples = lessons)
        GlobalScope.launch(Dispatchers.IO) {
            repository.setSettings(settingsModel = settings)
        }
    }

    fun daysIntToString(daysInt : List<Int>) : MutableList<String> {
        val daysString = mutableListOf<String>()
        for (dayInt in daysInt){
            when(dayInt){
                0 -> daysString.add("Monday")
                1 -> daysString.add("Tuesday")
                2 -> daysString.add("Wednesday")
                3 -> daysString.add("Thursday")
                4 -> daysString.add("Friday")
                5 -> daysString.add("Saturday")
                else -> daysString.add("Sunday")
            }
        }
        return daysString
    }

    fun daysStringToInt() : MutableList<Int> {
        val daysInt = mutableListOf<Int>()
        for (dayString in settings?.days!!){
            when(dayString){
                "Monday" -> daysInt.add(0)
                "Tuesday" -> daysInt.add(1)
                "Wednesday" -> daysInt.add(2)
                "Thursday" -> daysInt.add(3)
                "Friday" -> daysInt.add(4)
                "Saturday" -> daysInt.add(5)
                else -> daysInt.add(6)
            }
        }
        return mutableListOf(2)
    }

}