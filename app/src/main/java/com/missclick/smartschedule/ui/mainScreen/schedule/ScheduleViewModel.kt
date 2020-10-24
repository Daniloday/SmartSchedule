package com.missclick.smartschedule.ui.mainScreen.schedule

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.missclick.smartschedule.App
import com.missclick.smartschedule.adapters.groupie.AddLessonButtonItem
import com.missclick.smartschedule.adapters.groupie.LessonEmptyItem
import com.missclick.smartschedule.adapters.groupie.LessonItem
import com.missclick.smartschedule.data.datasource.local.entity.DayEntity
import com.missclick.smartschedule.data.models.*
import com.missclick.smartschedule.data.repository.ILessonRepository
import com.missclick.smartschedule.extensions.default
import com.missclick.smartschedule.viewstates.ScheduleViewStates
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ScheduleViewModel() : ViewModel() {

    @Inject
    lateinit var repository: ILessonRepository
    private var onPause: Int = 0 // 0 - not onPause, 1 - in add lesson, 2 - in info
    val stateData = MutableLiveData<ScheduleViewStates>().default(initialValue = ScheduleViewStates.LoadingState())

    init {
        App.appComponent.inject(this)
    }

    fun editSchedule(){
        stateData.value = ScheduleViewStates.LoadingState(true)

    }

    fun onResume(){
        if(onPause == 1) stateData.value = ScheduleViewStates.LoadingState(true)
        if(onPause == 2) stateData.value = ScheduleViewStates.LoadingState()
//        onPause = 0
        Log.e("OnResume",onPause.toString())
    }

    fun onPause(where : Int){
        onPause = where
    }

    fun saveSchedule(){
        stateData.value = ScheduleViewStates.LoadingState()
    }


    fun initData(edit : Boolean) {
        GlobalScope.launch(Dispatchers.IO) {
            val days1 : MutableList<MutableList<Item>> = mutableListOf()
            val days2 : MutableList<MutableList<Item>> = mutableListOf()
            initAllDays(daysItem = days1, edit = edit, week = 1)
            initAllDays(daysItem = days2, edit = edit, week = 2)
            withContext(Dispatchers.Main){
                if (edit) stateData.value = ScheduleViewStates.EditingState(days1,days2)
                else stateData.value = ScheduleViewStates.LoadedState(days1, days2)
                onPause(0)
            }
        }
    }

    private suspend fun initAllDays(daysItem: MutableList<MutableList<Item>>, edit : Boolean, week: Int){
        val days = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
        val daysEntity = repository.getAllDays()
        for(day in days) {
            val weekDay : MutableList<Item> = mutableListOf()
            for(couple in 1..4){
                val dayEntity = getLessonById(days = daysEntity, day = day, couple = couple, week = week)
                val lessonId = dayEntity?.lessonId
                if (lessonId != null)
                    if(edit)
                        weekDay.add(LessonItem(repository.getLessonById(lessonId),
                            edit = true,
                            callback = object : LessonItem.ItemClickCallback{
                                override val edit: Boolean
                                    get() = true
                                override fun onItemClicked() {
                                    GlobalScope.launch(Dispatchers.IO){
                                        repository.deleteDay(dayEntity)
                                        withContext(Dispatchers.Main){
                                            initData(edit = true)
                                            ScheduleViewStates.LoadingState(edit = true)
                                        }
                                    }
                                }
                            }))
                    else
                        weekDay.add(LessonItem(repository.getLessonById(lessonId),
                            edit = false,
                            callback = object : LessonItem.ItemClickCallback{
                                override val edit: Boolean
                                    get() = false
                                override fun onItemClicked() {
                                onPause(2)
                            }
                            }))
                else {
                    if(edit) weekDay.add(AddLessonButtonItem(day = day, couple = couple,week = week,
                        callback = object : AddLessonButtonItem.ItemClickCallback{
                            override fun onItemClicked() {
                                onPause(1)
                            }
                        }
                    ))
                    else weekDay.add(LessonEmptyItem())
                }
            }
            daysItem.add(weekDay)
        }
    }

    private fun getLessonById(days: List<ScheduleDayModel>, day: String, couple: Int, week: Int) : ScheduleDayModel? {
        for (dayEntity in days){
                if(dayEntity.dayName == day && dayEntity.couple == couple &&
                    (dayEntity.week == 0 || dayEntity.week == week))
                    return dayEntity
        }
        return null
    }
}