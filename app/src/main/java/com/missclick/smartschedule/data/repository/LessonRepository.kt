package com.missclick.smartschedule.data.repository

import android.util.Log
import com.google.gson.GsonBuilder
import com.missclick.smartschedule.data.datasource.local.LocalDataSource
import com.missclick.smartschedule.data.datasource.remote.RemoteDataSource
import com.missclick.smartschedule.data.map.mapDayEntityToScheduleDayModel
import com.missclick.smartschedule.data.map.mapLessonEntityToModel
import com.missclick.smartschedule.data.map.mapLessonModelToEntity
import com.missclick.smartschedule.data.map.mapScheduleDayModelToEntity
import com.missclick.smartschedule.data.models.LessonModel
import com.missclick.smartschedule.data.models.ScheduleDayModel


class LessonRepository(
    var local : LocalDataSource,
    var remote : RemoteDataSource
) : ILessonRepository{

    //function with lesson
    override suspend fun getLessons(): List<LessonModel> {
        val lessonEntities =  local.getLessonsAsync().await()
        val lessonModels = mutableListOf<LessonModel>()
        for(lesson in lessonEntities)
            lessonModels.add(mapLessonEntityToModel(lessonEntity = lesson))
        return lessonModels
    }

    override fun insertLesson(lessonModel : LessonModel) {
        val lessonEntity = mapLessonModelToEntity(lessonModel = lessonModel)
        local.insertLessonAsync(lessonEntity = lessonEntity)

    }

    override suspend fun getLessonById(lessonId : Int): LessonModel {
        val entity = local.getLessonByIdAsync(lessonId = lessonId).await()
        return mapLessonEntityToModel(lessonEntity = entity)
    }

    //function with scheduleDay
    override fun insertDay(scheduleDayModel: ScheduleDayModel) {
        val dayEntity = mapScheduleDayModelToEntity(scheduleDayModel = scheduleDayModel)
        local.insertLessonToScheduleAsync(dayEntity = dayEntity)
    }

    override suspend fun getAllDays(): List<ScheduleDayModel> {
        val dayEntities = local.getAllDaysAsync().await()
        val scheduleDayModels = mutableListOf<ScheduleDayModel>()
        for(day in dayEntities)
            scheduleDayModels.add(mapDayEntityToScheduleDayModel(dayEntity = day))
        return scheduleDayModels
    }

    override suspend fun deleteDay(scheduleDayModel: ScheduleDayModel) {
        val dayEntity = mapScheduleDayModelToEntity(scheduleDayModel = scheduleDayModel)
        local.deleteDayAsync(dayEntity = dayEntity)
    }

    //imp/exp
    override suspend fun exportSchedule():String?{
        val lessons =  local.getLessonsAsync().await()
        val dayEntities =  local.getAllDaysAsync().await()
        return remote.addScheduleToFirebase(lessons = lessons, dayEntities = dayEntities)
    }

    override suspend fun importSchedule(id : String){
        remote.importScheduleFromFirebase(id = id)
    }

}