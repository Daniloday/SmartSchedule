package com.missclick.smartschedule.adapters

import android.util.Log
import android.view.View
import android.widget.TextView
import com.missclick.smartschedule.R
import com.missclick.smartschedule.data.models.ScheduleModel
import tellh.com.recyclertreeview_lib.TreeNode
import tellh.com.recyclertreeview_lib.TreeViewBinder

class DayNodeBinder : TreeViewBinder<DayNodeBinder.ViewHolder>() {
    override fun provideViewHolder(itemView: View?): ViewHolder? {

        return itemView?.let { DayNodeBinder.ViewHolder(it) }
    }

    override fun bindView(holder: DayNodeBinder.ViewHolder?, p1: Int, node: TreeNode<*>?) {
        val lessonNode = node?.content as ScheduleModel
        Log.e("DayNode","1")
        holder?.tvName?.text = lessonNode.name
    }

    override fun getLayoutId(): Int {
        return R.layout.day_in_schedule_list_item
    }

    class ViewHolder(rootView: View) : TreeViewBinder.ViewHolder(rootView) {
        var tvName : TextView = rootView.findViewById(R.id.text_day_schedule_name)
    }

}