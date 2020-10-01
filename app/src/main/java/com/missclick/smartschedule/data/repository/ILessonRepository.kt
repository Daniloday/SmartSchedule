package com.missclick.smartschedule.data.repository

import com.missclick.smartschedule.data.datasource.local.entity.LessonEntity
import com.missclick.smartschedule.data.models.LessonModel
import com.missclick.smartschedule.data.models.Schedule

interface ILessonRepository {
    fun getSchedule() : Schedule
    suspend fun getLessons() : List<LessonModel>
    fun insertLesson(lessonEntity: LessonEntity)
}