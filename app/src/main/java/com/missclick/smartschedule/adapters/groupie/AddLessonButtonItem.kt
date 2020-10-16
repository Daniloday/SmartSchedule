package com.missclick.smartschedule.adapters.groupie

import com.missclick.smartschedule.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item

class AddLessonButtonItem(private val day: String, private val couple : Int) : Item() {


    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
    }

    override fun getLayout() = R.layout.schedule_add_lesson_to_schedule_list_item

}