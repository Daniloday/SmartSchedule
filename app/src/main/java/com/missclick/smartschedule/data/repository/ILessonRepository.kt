package com.missclick.smartschedule.data.repository

import com.missclick.smartschedule.data.models.LessonModel
import com.missclick.smartschedule.data.models.Schedule

interface ILessonRepository {
    fun getSchedule() : Schedule
    fun insertLesson(lessonModel: LessonModel)
}