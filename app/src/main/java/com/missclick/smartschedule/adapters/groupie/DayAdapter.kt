package com.missclick.smartschedule.adapters.groupie

import com.missclick.smartschedule.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.schedule_day.*
import kotlinx.android.synthetic.main.schedule_lesson.*
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem



class DayAdapter(private val title: String)
    : Item(), ExpandableItem{

    private lateinit var expandableGroup: ExpandableGroup



    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
//        viewHolder.text_day_schedule.text = title
//        viewHolder.item_expandable_header_icon.setImageResource(getRotatedIconResId())

        viewHolder.item_expandable_header_root.setOnClickListener {
            expandableGroup.onToggleExpanded()
//            viewHolder.item_expandable_header_icon.setImageResource(getRotatedIconResId())
        }
    }

    override fun getLayout() = R.layout.schedule_lesson

    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
        expandableGroup = onToggleListener
    }

//    private fun getRotatedIconResId() =
//        if (expandableGroup.isExpanded)
//            R.drawable.ic_keyboard_arrow_up_black_24dp
//        else
//            R.drawable.ic_keyboard_arrow_down_black_24dp
}