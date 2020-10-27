package com.missclick.smartschedule.adapters.groupie


import android.view.View
import androidx.navigation.findNavController
import com.missclick.smartschedule.R
import com.missclick.smartschedule.data.models.LessonModel
import com.missclick.smartschedule.ui.lessons.info.LessonInfoFragment
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.schedule_lesson_in_schedule_item.*


class LessonItem(private val lesson: LessonModel,
                 private val callback: ItemClickCallback,
                 private val edit: Boolean) : Item() {


    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.text_name_list_lesson_in_schedule_item.text = lesson.lessonName
        viewHolder.text_type_lesson_in_schedule_item.text = lesson.type
        if(edit) viewHolder.text_delete_lesson.visibility = View.VISIBLE
        else viewHolder.text_delete_lesson.visibility = View.INVISIBLE
        viewHolder.card_view_schedule_lesson.setOnClickListener{
            if(!callback.edit){
                it.findNavController().navigate(
                    R.id.lesson_info_fragment,
                    LessonInfoFragment.newInstance(
                        lesson = lesson
                    )
                )
            }

            callback.onItemClicked()
        }
    }

    override fun getLayout() = R.layout.schedule_lesson_in_schedule_item

    interface ItemClickCallback{
        val edit : Boolean
        fun onItemClicked()
    }
}