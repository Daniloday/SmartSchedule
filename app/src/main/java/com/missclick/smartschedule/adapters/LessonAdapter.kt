package com.missclick.smartschedule.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.missclick.smartschedule.R
import com.missclick.smartschedule.data.models.LessonModel

class LessonAdapter(var items: List<LessonModel>) : RecyclerView.Adapter<LessonAdapter.MainHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_lesson_item, parent, false))
    override fun getItemCount() = items.size
    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(items[position])
    }
    inner class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name = itemView.findViewById<TextView>(R.id.text_name_list_lesson_item)
        fun bind(item: LessonModel) {
            name.text = item.lessonName
        }
    }
}