package com.missclick.smartschedule.data.repository

import com.missclick.smartschedule.data.datasource.local.entity.DayEntity
import com.missclick.smartschedule.data.datasource.local.entity.LessonEntity
import com.missclick.smartschedule.data.models.LessonModel
import com.missclick.smartschedule.data.models.Schedule

interface ILessonRepository {
    fun getSchedule() : Schedule
    suspend fun getLessons() : List<LessonModel>
    fun insertLesson(lessonEntity: LessonEntity)
    fun insertDay(dayEntity: DayEntity)
    suspend fun getAllDays() : List<DayEntity>
    suspend fun getLessonById(lessonId : Int) : LessonModel
    suspend fun deleteDay(dayEntity: DayEntity)
}