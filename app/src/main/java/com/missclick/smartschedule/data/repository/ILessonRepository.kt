package com.missclick.smartschedule.data.repository

import com.missclick.smartschedule.data.models.LessonModel
import com.missclick.smartschedule.data.models.ScheduleDayModel

interface ILessonRepository {

    // with lesson
    suspend fun getLessons() : List<LessonModel>
    fun insertLesson(lessonModel : LessonModel)
    suspend fun getLessonById(lessonId : Int) : LessonModel

    //with scheduleDay
    fun insertDay(scheduleDayModel : ScheduleDayModel)
    suspend fun getAllDays() : List<ScheduleDayModel>
    suspend fun deleteDay(scheduleDayModel: ScheduleDayModel)

    //exp/imp
    suspend fun exportSchedule(): String?
    suspend fun importSchedule(id : String)
}