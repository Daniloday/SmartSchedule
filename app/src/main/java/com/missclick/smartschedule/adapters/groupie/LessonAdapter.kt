package com.missclick.smartschedule.adapters.groupie


import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView
import com.missclick.smartschedule.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.schedule_lesson.*


class LessonAdapter(
                    private val number: Int)
    : Item(){


    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.text_schedule_lesson.text = number.toString()
    }

    override fun getLayout() = R.layout.schedule_lesson

    override fun getSpanSize(spanCount: Int, position: Int) = spanCount / 1
}