package com.missclick.smartschedule.adapters.groupie

import androidx.navigation.findNavController
import com.missclick.smartschedule.R
import com.missclick.smartschedule.ui.lessons.LessonsFragment
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.schedule_add_lesson_to_schedule_list_item.*

class AddLessonButtonItem(private val day: String, private val couple : Int) : Item() {


    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.button_add_lesson_to_schedule.setOnClickListener{
            it.findNavController()?.navigate(
                R.id.nav_lessons,
                LessonsFragment.newInstance(
                    day = day, couple = couple
                )
            )
        }
    }

    override fun getLayout() = R.layout.schedule_add_lesson_to_schedule_list_item


}