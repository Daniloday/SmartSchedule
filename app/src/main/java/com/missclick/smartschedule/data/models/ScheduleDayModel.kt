package com.missclick.smartschedule.data.models

class ScheduleDayModel(
    var id : Int? = null,
    var dayName : String,
    var lessonId : Int,
    var couple : Int,
    var week : Int
)