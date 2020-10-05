package com.missclick.smartschedule.data.models

import android.os.Parcelable
import java.io.Serializable

data class LessonModel(
    var id : Int? = null,
    var lessonName : String,
    var teacherName : String,
    //var times : List<LessonTime>,
    var links : Map<String, String>,
    var description : String
    //var week : Int
):Serializable

data class LessonTime(
    var time : String,
    var date : String
)