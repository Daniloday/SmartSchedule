package com.missclick.smartschedule.data.models

import com.missclick.smartschedule.R
import tellh.com.recyclertreeview_lib.LayoutItemType

class ScheduleModel(var name : String) : LayoutItemType{
    override fun getLayoutId(): Int {
        return R.layout.day_in_schedule_list_item
    }

}

class LessonInSchedule(var lessonModel: LessonModel) : LayoutItemType{
    override fun getLayoutId(): Int {
        return R.layout.schedule_lesson_in_schedule
    }

}

class AddLessonToScheduleModel(val day : String, val couple: Int) : LayoutItemType{
    override fun getLayoutId(): Int {
        return R.layout.schedule_add_lesson_to_schedule_list_item
    }

}