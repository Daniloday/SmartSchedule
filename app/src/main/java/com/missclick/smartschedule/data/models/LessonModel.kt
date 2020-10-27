package com.missclick.smartschedule.data.models

import java.io.Serializable

data class LessonModel(
    var id : Int? = null,
    var lessonName : String,
    var teacherName : String,
    var type: String,
    var links : MutableMap<String, String>,
    var description : String
):Serializable

