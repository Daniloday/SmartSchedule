package com.missclick.smartschedule.data.models

import java.io.Serializable

data class ShortLessonModel(
    var id : Int? = null,
    var lessonName : String,
    var teacherName : String,
    var type: String
): Serializable