package com.missclick.smartschedule.data.datasource.local

import android.provider.Settings
import com.missclick.smartschedule.data.datasource.local.entity.DayEntity
import com.missclick.smartschedule.data.datasource.local.entity.LessonEntity
import kotlinx.coroutines.*

class LocalDataSource(var database : ScheduleDatabase){

    fun getLessonsAsync() : Deferred<List<LessonEntity>> {
         return GlobalScope.async {
             database.lessonsDao().getAllLessons()
         }
    }

    fun insertLessonAsync(lessonEntity: LessonEntity){
        GlobalScope.async {
            database.lessonsDao().insertLesson(lessonEntity = lessonEntity)
        }
    }

    fun insertLessonToScheduleAsync(dayEntity: DayEntity){
        GlobalScope.async {
            database.dayDao().insertDay(dayEntity = dayEntity)
        }
    }

    fun getAllDaysAsync() : Deferred<List<DayEntity>> {
        return GlobalScope.async {
            database.dayDao().getAll()
        }
    }

    fun getLessonByIdAsync(lessonId: Int) : Deferred<LessonEntity>{
        return GlobalScope.async {
            database.lessonsDao().getLessonById(id = lessonId)
        }
    }

    fun deleteDayAsync(dayEntity: DayEntity){
        GlobalScope.async {
            database.dayDao().removeDay(dayEntity = dayEntity)
        }
    }
}