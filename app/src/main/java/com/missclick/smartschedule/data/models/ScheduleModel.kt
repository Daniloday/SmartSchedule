package com.missclick.smartschedule.data.models

import com.missclick.smartschedule.R
import tellh.com.recyclertreeview_lib.LayoutItemType

class ScheduleModel(var name : String) : LayoutItemType{
    override fun getLayoutId(): Int {
        return R.layout.list_lesson_item
    }

}