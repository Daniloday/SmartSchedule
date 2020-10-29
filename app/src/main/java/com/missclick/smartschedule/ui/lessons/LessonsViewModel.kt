package com.missclick.smartschedule.ui.lessons

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.missclick.smartschedule.App
import com.missclick.smartschedule.data.models.LessonModel
import com.missclick.smartschedule.data.models.ScheduleDayModel
import com.missclick.smartschedule.data.repository.ILessonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LessonsViewModel : ViewModel() {

    @Inject
    lateinit var repository: ILessonRepository
    var lessonsLiveData = MutableLiveData<List<LessonModel>>()
    var refreshing = MutableLiveData<Boolean>().apply {
        value = false
    }

    init {
        App.appComponent.inject(this)
    }

    fun addLessonToSchedule(day : String, couple : Int, lessonModel: LessonModel, week : Int){
        GlobalScope.launch(Dispatchers.IO){
            lessonModel.id?.let {
                ScheduleDayModel(dayName = day, lessonId = it, couple = couple, week = week)
            }?.let {
                repository.insertDay(scheduleDayModel = it)
            }
        }
    }

     fun getLessons(){
         refreshing.value = true
        GlobalScope.launch(Dispatchers.IO) {
            val lessons = repository.getLessons()
            withContext(Dispatchers.Main){
                lessonsLiveData.value = lessons
                refreshing.value = false
            }
        }
    }

    fun removeLesson(){

    }

}