package com.missclick.smartschedule.ui.lessons

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.missclick.smartschedule.App
import com.missclick.smartschedule.data.datasource.local.entity.DayEntity
import com.missclick.smartschedule.data.datasource.local.entity.LessonEntity
import com.missclick.smartschedule.data.models.LessonModel
import com.missclick.smartschedule.data.models.ScheduleDayModel
import com.missclick.smartschedule.data.repository.ILessonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.DayOfWeek
import javax.inject.Inject

class LessonsViewModel : ViewModel() {
    @Inject
    lateinit var repository: ILessonRepository

    //var lessons : List<LessonModel>? = null
    var lessonsLiveData = MutableLiveData<List<LessonModel>>()


    init {
        App.appComponent.inject(this)
        //lessonsLiveData.value = lessons
    }

    fun addLessonToSchedule(day : String, couple : Int, lessonModel: LessonModel, week : Int){
        GlobalScope.launch(Dispatchers.IO){
            lessonModel.id?.let {
                ScheduleDayModel(dayName = day, lessonId = it, couple = couple, week = week)
//                DayEntity(dayName = day, lessonId = it, couple = couple, week = week)
            }?.let {
                repository.insertDay(scheduleDayModel = it)
            }
        }
    }

     fun getLessons(){
        GlobalScope.launch(Dispatchers.IO) {
            //Thread.sleep(1000)
//            var lessons = listOf(LessonModel(0,
//                "kek",
//                "anime",
//                mapOf("zoom" to "link"),
//                "mem"
//            ))
            val lessons = repository.getLessons()
            withContext(Dispatchers.Main){
                lessonsLiveData.value = lessons
            }
        }
    }
}