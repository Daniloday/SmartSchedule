package com.missclick.smartschedule.adapters

import android.view.View
import android.widget.TextView
import com.missclick.smartschedule.R
import com.missclick.smartschedule.data.models.LessonInSchedule
import com.missclick.smartschedule.data.models.ScheduleModel
import tellh.com.recyclertreeview_lib.TreeNode
import tellh.com.recyclertreeview_lib.TreeViewBinder

class EmptyLessonsNodeBinder : TreeViewBinder<EmptyLessonsNodeBinder.ViewHolder>() {
    override fun provideViewHolder(itemView: View?): ViewHolder? {
        return itemView?.let { EmptyLessonsNodeBinder.ViewHolder(it) }
    }

    override fun bindView(holder: EmptyLessonsNodeBinder.ViewHolder?, p1: Int, node: TreeNode<*>?) {
//        val lessonNode = node?.content as LessonInSchedule
//        holder?.tvName?.text = lessonNode.lessonModel.lessonName
    }

    override fun getLayoutId(): Int {
        return R.layout.schedule_lesson_empty
    }

    class ViewHolder(rootView: View) : TreeViewBinder.ViewHolder(rootView) {
//        var tvName : TextView = rootView.findViewById(R.id.text_name_list_lesson_in_schedule_item)
    }

}