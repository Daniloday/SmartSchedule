package com.missclick.smartschedule.adapters

import android.view.View
import android.widget.TextView
import com.missclick.smartschedule.R
import com.missclick.smartschedule.data.models.AddLessonToScheduleModel
import com.missclick.smartschedule.data.models.LessonInSchedule
import com.missclick.smartschedule.data.models.LessonModel
import tellh.com.recyclertreeview_lib.TreeNode
import tellh.com.recyclertreeview_lib.TreeViewBinder

class LessonsNodeBinder(var callback: Callback) : TreeViewBinder<LessonsNodeBinder.ViewHolder>() {
    override fun provideViewHolder(itemView: View?): ViewHolder? {
        return itemView?.let { LessonsNodeBinder.ViewHolder(it) }
    }

    override fun bindView(holder: LessonsNodeBinder.ViewHolder?, p1: Int, node: TreeNode<*>?) {
        val lessonNode = node?.content as LessonInSchedule
        holder?.tvName?.text = lessonNode.lessonModel.lessonName
        holder?.tvType?.text = lessonNode.lessonModel.type
        val btnNode = node?.content as LessonInSchedule
        holder?.itemView?.setOnClickListener {
            callback.onItemClicked(btnNode)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.schedule_lesson_in_schedule
    }

    class ViewHolder(rootView: View) : TreeViewBinder.ViewHolder(rootView) {
        var tvName : TextView = rootView.findViewById(R.id.text_name_list_lesson_in_schedule_item)
        var tvType = rootView.findViewById<TextView>(R.id.text_type_lesson_in_schedule_item)
    }

    interface Callback {
        fun onItemClicked(item : LessonInSchedule)
    }

}