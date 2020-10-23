package com.missclick.smartschedule.data.repository

import com.missclick.smartschedule.data.datasource.local.LocalDataSource
import com.missclick.smartschedule.data.datasource.local.entity.DayEntity
import com.missclick.smartschedule.data.datasource.local.entity.LessonEntity
import com.missclick.smartschedule.data.datasource.remote.RemoteDataSource
import com.missclick.smartschedule.data.map.mapLessonEntityToModel
import com.missclick.smartschedule.data.models.LessonModel
import com.missclick.smartschedule.data.models.Schedule

class LessonRepository(
    var local : LocalDataSource,
    var remote : RemoteDataSource
) : ILessonRepository{

    override fun getSchedule(): Schedule {
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

    override fun insertDay(dayEntity: DayEntity) {
        local.insertLessonToScheduleAsync(dayEntity = dayEntity)
    }

    override suspend fun getAllDays(): List<DayEntity> {
        return local.getAllDaysAsync().await()
    }

    override suspend fun getLessonById(lessonId : Int): LessonModel {
        val entity = local.getLessonByIdAsync(lessonId = lessonId).await()
        return mapLessonEntityToModel(lessonEntity = entity)
    }

    override suspend fun deleteDay(dayEntity: DayEntity) {
        local.deleteDayAsync(dayEntity = dayEntity)
    }

}