package com.missclick.smartschedule.adapters

import android.view.View
import android.widget.Button
import android.widget.TextView
import com.missclick.smartschedule.R
import com.missclick.smartschedule.data.models.AddLessonToScheduleModel
import com.missclick.smartschedule.data.models.LessonModel
import com.missclick.smartschedule.data.models.ScheduleModel
import tellh.com.recyclertreeview_lib.TreeNode
import tellh.com.recyclertreeview_lib.TreeViewBinder

class LessonToScheduleNodeBinder(var callback: Callback) : TreeViewBinder<LessonToScheduleNodeBinder.ViewHolder>() {
    override fun provideViewHolder(itemView: View?): ViewHolder? {
        return itemView?.let { LessonToScheduleNodeBinder.ViewHolder(it) }
    }

    override fun bindView(holder: LessonToScheduleNodeBinder.ViewHolder?, p1: Int, node: TreeNode<*>?) {
        val btnNode = node?.content as AddLessonToScheduleModel
        holder?.btnNode?.setOnClickListener {
            callback.onItemClicked(btnNode)
        }
        //val lessonNode = node?.content as ScheduleModel
        //holder?.tvName?.text = lessonNode.name
    }

    override fun getLayoutId(): Int {
        return R.layout.schedule_add_lesson_to_schedule_list_item
    }

    class ViewHolder(rootView: View) : TreeViewBinder.ViewHolder(rootView) {
        var btnNode : Button = rootView.findViewById(R.id.button_add_lesson_to_schedule)
    }

    interface Callback {
        fun onItemClicked(item : AddLessonToScheduleModel)
    }
}

