package com.missclick.smartschedule.data.models

data class LessonModel(
    var lessonName : String,
    var teacherName : String,
    var times : List<LessonTime>,
    var links : Map<String, String>,
    var description : String,
    var week : Int
)

data class LessonTime(
    var time : String,
    var date : String
)