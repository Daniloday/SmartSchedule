package com.missclick.smartschedule.data.repository

import com.missclick.smartschedule.data.datasource.local.LocalDataSource
import com.missclick.smartschedule.data.datasource.remote.RemoteDataSource
import com.missclick.smartschedule.data.datasource.remote.remoteModels.ScheduleFB
import com.missclick.smartschedule.data.map.*
import com.missclick.smartschedule.data.models.LessonModel
import com.missclick.smartschedule.data.models.ScheduleDayModel
import com.missclick.smartschedule.data.models.SettingsModel


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

    override suspend fun deleteLesson(lessonModel: LessonModel) {
        val lessonEntity = mapLessonModelToEntity(lessonModel = lessonModel)
        val dayEntities = local.getAllDaysAsync().await()
        for (day in dayEntities){
            if (day.lessonId == lessonEntity.id) local.deleteDayAsync(day)
        }
        local.deleteLessonAsync(lessonEntity = lessonEntity)
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

    override suspend fun isOnline(): Boolean {
        return remote.isOnlineAsync().await()
    }

    //imp/exp
    override suspend fun exportSchedule() : String?{
        val lessons =  local.getLessonsAsync().await()
        val dayEntities =  local.getAllDaysAsync().await()
        val a = remote.addScheduleToFirebase(lessons = lessons, dayEntities = dayEntities).await()
        return a
    }

    override suspend fun importSchedule(id : String){
        remote.importScheduleFromFirebase(id = id , callback = object : Callback{
            override fun insertDaysAndLesson(schedule: ScheduleFB) {
                local.deleteAllDays()
                local.deleteAllLessons()
                for (day in schedule.days!!)
                    local.insertLessonToScheduleAsync(dayEntity = day)
                for (lesson in schedule.lessons!!)
                    local.insertLessonAsync(lessonEntity = lesson)
            }
        })
    }

    //settings

    override suspend fun getSettings(): SettingsModel {
        val settingsEntities =  local.getSettingsAsync().await()
        return mapSettingsEntityToSettingsModel(settingsEntity = settingsEntities.last())
    }

    override suspend fun setSettings(settingsModel: SettingsModel) {
        val settingsEntity = mapSettingsModelToEntity(settingsModel = settingsModel)
        local.insertSettingsAsync(settings = settingsEntity)
    }

    interface Callback {
        fun insertDaysAndLesson(schedule : ScheduleFB)
    }

}