package com.missclick.smartschedule.data.map

import com.missclick.smartschedule.data.datasource.local.entity.LessonEntity
import com.missclick.smartschedule.data.models.LessonModel

fun mapLessonEntityToModel(lessonEntity: LessonEntity) : LessonModel {
    val linksStr = lessonEntity.links
    val links = strToMap(linksStr)
    return LessonModel(
        id = lessonEntity.id,
        lessonName = lessonEntity.name,
        teacherName = lessonEntity.teacherName,
        links = links,
        description = lessonEntity.description
    )
}

fun mapLessonModelToEntity(lessonModel: LessonModel) : LessonEntity{
    val linksStr = lessonModel.links
    val links = mapToStr(linksStr)
    return LessonEntity(
        id = lessonModel.id,
        name = lessonModel.lessonName,
        teacherName = lessonModel.teacherName,
        links = links,
        description = lessonModel.description
    )
}

//map() -> "zoom":"link","tg":"link" ...
fun mapToStr(map : Map<String, String>) : String{
    return "zoom:link"
}

// "zoom":"link","tg":"link" ... -> map()
fun strToMap(str : String) : Map<String, String>{
    val links = mutableMapOf<String, String>()
    val splitedStr = str.split(",")
    for(cell in splitedStr){
        val splited2 = cell.split(":")
        links[splited2[0]] = splited2[1]
    }
    return links
}