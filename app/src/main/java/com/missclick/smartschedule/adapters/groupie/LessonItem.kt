package com.missclick.smartschedule.adapters.groupie


import androidx.annotation.ColorInt
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.missclick.smartschedule.R
import com.missclick.smartschedule.data.models.LessonModel
import com.missclick.smartschedule.ui.lessons.LessonsFragment
import com.missclick.smartschedule.ui.lessons.info.LessonInfoFragment
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.schedule_add_lesson_to_schedule_list_item.*
import kotlinx.android.synthetic.main.schedule_lesson.*
import kotlinx.android.synthetic.main.schedule_lesson_in_schedule.*


class LessonItem(private val lesson: LessonModel, private val callback: LessonItem.ItemClickCallback) : Item() {


    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.text_name_list_lesson_in_schedule_item.text = lesson.lessonName
        viewHolder.text_type_lesson_in_schedule_item.text = lesson.type
        viewHolder.card_view_schedule_lesson.setOnClickListener{
            it.findNavController()?.navigate(
                R.id.lessonInfoFragment,
                LessonInfoFragment.newInstance(
                    param = lesson
                )
            )
            callback.onItemClicked()
        }
    }

    override fun getLayout() = R.layout.schedule_lesson_in_schedule

    interface ItemClickCallback{
        fun onItemClicked()
    }
}