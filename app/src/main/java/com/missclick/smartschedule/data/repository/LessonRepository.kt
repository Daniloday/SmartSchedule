package com.missclick.smartschedule.data.repository

import android.util.Log
import com.missclick.smartschedule.data.datasource.local.LocalDataSource
import com.missclick.smartschedule.data.datasource.local.entity.LessonEntity
import com.missclick.smartschedule.data.datasource.remote.RemoteDataSource
import com.missclick.smartschedule.data.map.mapLessonEntityToModel
import com.missclick.smartschedule.data.map.mapLessonModelToEntity
import com.missclick.smartschedule.data.models.LessonModel
import com.missclick.smartschedule.data.models.Schedule
import javax.inject.Inject

class LessonRepository(
    var local : LocalDataSource,
    var remote : RemoteDataSource
) : ILessonRepository{

    override fun getSchedule(): Schedule {
        Log.e("Repository", "Keklol")
        return Schedule()
    }

    override suspend fun getLessons(): List<LessonModel> {
        val lessonsEntities =  local.getLessonsAsync().await()
        val lessonsModels = mutableListOf<LessonModel>()
        for(lesson in lessonsEntities)
            lessonsModels.add(mapLessonEntityToModel(lessonEntity = lesson))
        return lessonsModels
    }

    override fun insertLesson(lessonEntity: LessonEntity) {
        //local.insertLessonAsync(lessonEntity = mapLessonModelToEntity(lessonModel))
        local.insertLessonAsync(lessonEntity = lessonEntity)

    }

}