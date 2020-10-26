package com.missclick.smartschedule.ui.lessons.addLesson

import androidx.lifecycle.ViewModel
import com.missclick.smartschedule.App
import com.missclick.smartschedule.data.datasource.local.entity.LessonEntity
import com.missclick.smartschedule.data.models.LessonModel
import com.missclick.smartschedule.data.repository.ILessonRepository
import javax.inject.Inject

class AddLessonViewModel : ViewModel() {

    @Inject
    lateinit var repository: ILessonRepository

    init {
        App.appComponent.inject(this)
    }

    fun saveLesson(lessonName: String, teacherName: String, links: MutableMap<String, String>, description: String, type: String){
        val lesson = LessonModel(
            lessonName = lessonName,
            teacherName = teacherName,
            type = type,
            links = links,
            description = description
        )
//        val entity = LessonEntity(name = lessonName, teacherName = teacherName, links = "link", description = description, type = type)
        repository.insertLesson(lessonModel = lesson)
    }
}