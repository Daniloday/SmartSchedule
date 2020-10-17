package com.missclick.smartschedule.adapters.groupie


import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView
import com.missclick.smartschedule.R
import com.missclick.smartschedule.data.models.LessonModel
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.schedule_lesson.*
import kotlinx.android.synthetic.main.schedule_lesson_in_schedule.*


class LessonItem(private val lesson: LessonModel) : Item() {


    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.text_name_list_lesson_in_schedule_item.text = lesson.lessonName
        viewHolder.text_type_lesson_in_schedule_item.text = lesson.type
    }

    override fun getLayout() = R.layout.schedule_lesson_in_schedule

}