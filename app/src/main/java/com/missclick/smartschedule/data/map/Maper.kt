package com.missclick.smartschedule.data.map

import android.util.Log
import com.missclick.smartschedule.data.datasource.local.entity.DayEntity
import com.missclick.smartschedule.data.datasource.local.entity.LessonEntity
import com.missclick.smartschedule.data.models.LessonModel
import com.missclick.smartschedule.data.models.ScheduleDayModel

fun mapLessonEntityToModel(lessonEntity: LessonEntity) : LessonModel {
    val linksStr = lessonEntity.links
    val links = strToMap(linksStr)
    return LessonModel(
        id = lessonEntity.id,
        lessonName = lessonEntity.name,
        teacherName = lessonEntity.teacherName,
        type = lessonEntity.type,
        links = links,
        description = lessonEntity.description
    )
}

fun mapLessonModelToEntity(lessonModel: LessonModel) : LessonEntity{
    val links = mapToStr(lessonModel.links)
    return LessonEntity(
        id = lessonModel.id,
        name = lessonModel.lessonName,
        teacherName = lessonModel.teacherName,
        type = lessonModel.type,
        links = links,
        description = lessonModel.description
    )
}

fun mapDayEntityToScheduleDayModel(dayEntity: DayEntity) : ScheduleDayModel{
    return ScheduleDayModel(
        id = dayEntity.id,
        dayName = dayEntity.dayName,
        lessonId = dayEntity.lessonId,
        couple = dayEntity.couple,
        week = dayEntity.week
    )
}

fun mapScheduleDayModelToEntity(scheduleDayModel: ScheduleDayModel) : DayEntity{
    return DayEntity(
        id = scheduleDayModel.id,
        dayName = scheduleDayModel.dayName,
        lessonId = scheduleDayModel.lessonId,
        couple = scheduleDayModel.couple,
        week = scheduleDayModel.week
    )
}

//map() -> "zoom"::"link","tg"::"link" ...
fun mapToStr(map : Map<String, String>) : String{
    var links = ""
    for((key, value) in map){
        links += "$key::$value,"
    }
    links = links.dropLast(1)
    return links
}

// "zoom"::"link","tg"::"link" ... -> map()
fun strToMap(strLinks : String) : MutableMap<String, String>{
    val links = mutableMapOf<String, String>()
    val couples = strLinks.split(",")
    for(couple in couples){
        val splitCouple = couple.split("::")
        links[splitCouple[0]] = splitCouple[1]
    }
    return links
}