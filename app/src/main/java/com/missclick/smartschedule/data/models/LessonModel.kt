package com.missclick.smartschedule.data.models

import android.os.Parcelable
import java.io.Serializable

data class LessonModel(
    var id : Int? = null,
    var lessonName : String,
    var teacherName : String,
    var type: String,
    var links : Map<String, String>,
    var description : String
):Serializable

