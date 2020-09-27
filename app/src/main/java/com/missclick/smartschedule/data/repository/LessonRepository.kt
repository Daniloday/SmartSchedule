package com.missclick.smartschedule.data.repository

import com.missclick.smartschedule.data.datasource.local.LocalDataSource
import com.missclick.smartschedule.data.datasource.remote.RemoteDataSource
import com.missclick.smartschedule.data.models.LessonModel
import com.missclick.smartschedule.data.models.Schedule

class LessonRepository(
    val local : LocalDataSource,
    val remote : RemoteDataSource
) : ILessonRepository{

    override fun getSchedule(): Schedule {
        return Schedule()
    }

    override fun insertLesson(lessonModel: LessonModel) {

    }

}