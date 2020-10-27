package com.missclick.smartschedule.adapters.groupie

import com.missclick.smartschedule.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item

class LessonEmptyItem() : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
    }

    override fun getLayout() = R.layout.schedule_lesson_empty_item

}