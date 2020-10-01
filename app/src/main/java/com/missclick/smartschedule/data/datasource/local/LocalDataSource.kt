package com.missclick.smartschedule.data.datasource.local

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
            database.lessonsDao().insertLesson(lessonEntity)
        }
    }
}