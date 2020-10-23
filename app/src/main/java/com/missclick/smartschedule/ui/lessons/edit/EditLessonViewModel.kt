package com.missclick.smartschedule.ui.lessons.edit

import androidx.lifecycle.ViewModel
import com.missclick.smartschedule.App
import com.missclick.smartschedule.data.datasource.local.entity.LessonEntity
import com.missclick.smartschedule.data.models.LessonModel
import com.missclick.smartschedule.data.repository.ILessonRepository
import javax.inject.Inject

class EditLessonViewModel : ViewModel() {

    @Inject
    lateinit var repository: ILessonRepository

    init {
        App.appComponent.inject(this)
    }

    fun editLesson(lesson : LessonModel){
//        val entity = LessonEntity(name = lesson.lessonName, teacherName = lesson.teacherName, links = "Zoom:default", description = lesson.description, id = lesson.id, type = "Labs")
        repository.insertLesson(lesson)
    }
}