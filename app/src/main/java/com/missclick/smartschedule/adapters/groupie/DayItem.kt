package com.missclick.smartschedule.adapters.groupie

import com.missclick.smartschedule.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.schedule_day.*
import kotlinx.android.synthetic.main.schedule_lesson.*
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import kotlinx.android.synthetic.main.day_in_schedule_list_item.*
import kotlinx.android.synthetic.main.schedule_lesson_in_schedule.*


class DayItem(private val dayName: String)
    : Item(), ExpandableItem{

    private lateinit var expandableGroup: ExpandableGroup

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.text_day_schedule_name.text = dayName
        viewHolder.day_in_schedule_root.setOnClickListener {
            expandableGroup.onToggleExpanded()
        }
    }

    override fun getLayout() = R.layout.day_in_schedule_list_item

    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
        expandableGroup = onToggleListener
    }

}